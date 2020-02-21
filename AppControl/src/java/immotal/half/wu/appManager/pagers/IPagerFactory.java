package immotal.half.wu.appManager.pagers;

import com.sun.istack.internal.NotNull;
import immotal.half.wu.appManager.pagers.impls.DefaultPager;
import immotal.half.wu.appManager.pagers.intefaces.IPageProcess;

public class IPagerFactory {

    public static <ResultType> DefaultPager<ResultType> create(
            @NotNull String pageName,
            @NotNull String pageDoc,
            @NotNull IPageProcess<ResultType> process
    ) {
        return new DefaultPager<>(pageName, pageDoc, process);
    }

}
