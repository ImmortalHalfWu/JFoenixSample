package immortal.half.wu.devices;

import immortal.half.wu.LogUtil;
import immortal.half.wu.PhoneManagerUtil;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.executor.ExecutorManager;
import immortal.half.wu.executor.interfaces.IJobListener;
import immortal.half.wu.executor.interfaces.IJobWithTimeOut;
import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;

import java.util.Arrays;
import java.util.HashSet;

public class PhoneStatueReader {

    private static PhoneStatueReader deviceListener;

    public static PhoneStatueReader getInstance() {
        if (deviceListener == null) {
            synchronized (PhoneStatueReader.class) {
                if (deviceListener == null) {
                    deviceListener = new PhoneStatueReader();
                }
            }
        }
        return deviceListener;
    }


    private static final long LOOPER_READ_DELAY_TIME = 2000;
    private final ITimeOutExecutorService executorService;
    private final HashSet<String> cacheConnectDevices;
    private final HashSet<String> newConnectDevices;
    private final HashSet<String> connectChangeDevices;

    private PhoneStatueReader() {
        cacheConnectDevices = new HashSet<>();
        newConnectDevices = new HashSet<>();
        connectChangeDevices = new HashSet<>();
        executorService = ExecutorManager.createTimeOutExecutorService(getClass().getSimpleName());
        startRead();
    }

    private void startRead() {
        try {
            Thread.sleep(LOOPER_READ_DELAY_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.executeTimeOut120s(readConnectDeviceJob, readConnectDeviceListener);
    }

    private IJobWithTimeOut<String[]> readConnectDeviceJob = () -> ADBManager.getInstance().adbFindAllDevice();

    private IJobListener<String[]> readConnectDeviceListener = new IJobListener<String[]>() {
        @Override
        public void onError(Exception e) {
            LogUtil.e(PhoneManagerUtil.TAG, "PhoneStatueReader : 设备状态轮询异常," + e.getMessage());
            startRead();
        }

        @Override
        public void onComplete(String[] result) {

            newConnectDevices.addAll(Arrays.asList(result));
            connectChangeDevices.addAll(cacheConnectDevices);
            connectChangeDevices.removeAll(newConnectDevices);
            // 最新的连接状态中没有， 但缓存的连接状态有， 则说明有设备断开
            if (connectChangeDevices.size() > 0) {
                LogUtil.i(PhoneManagerUtil.TAG, "PhoneStatueReader : 有设备断开：" + connectChangeDevices);
            }
            for (String disconnectDeviceId : connectChangeDevices) {
                try {
                    deviceStateListener.deviceDisconnect(disconnectDeviceId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            connectChangeDevices.clear();
            connectChangeDevices.addAll(newConnectDevices);
            connectChangeDevices.removeAll(cacheConnectDevices);
            // 最新的连接状态中有， 但缓存的连接状态没有有， 则说明有设备连接
            if (connectChangeDevices.size() > 0) {
                LogUtil.i(PhoneManagerUtil.TAG, "PhoneStatueReader : 有新的设备连接：" + connectChangeDevices);
            }
            for (String connectDeviceId : connectChangeDevices) {
                try {
                    deviceStateListener.deviceConnect(connectDeviceId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            connectChangeDevices.clear();
            cacheConnectDevices.clear();
            cacheConnectDevices.addAll(newConnectDevices);
            newConnectDevices.clear();
            startRead();
        }
    };

    private StatueListener deviceStateListener = new StatueListener() {
        @Override
        public void deviceConnect(String deviceId) throws Exception { }

        @Override
        public void deviceDisconnect(String deviceId) throws Exception { }
    };

    public void setListeners(StatueListener listeners) {
        if (listeners != null) {
            this.deviceStateListener = listeners;
        }
    }

    public interface StatueListener {
        void deviceConnect(String deviceId) throws Exception;
        void deviceDisconnect(String deviceId) throws Exception;
    }
}
