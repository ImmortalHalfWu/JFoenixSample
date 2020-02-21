package immortal.half.wu;

import immortal.half.wu.devices.interfaces.IAndroidDevice;

public interface IPhoneConnectListener {

    void deviceConnect(String deviceId, IAndroidDevice androidDevice) throws Exception;
    void deviceDisConnect(String deviceId, IAndroidDevice androidDevice) throws Exception;

}
