package springnz.orientdb.plugin;

import com.orientechnologies.common.log.OLogManager;
import com.orientechnologies.orient.core.sql.OSQLEngine;
import springnz.orientdb.plugin.functions.DateTimePlusSecondsFunction;
import springnz.orientdb.plugin.functions.PowFunction;

public class OSQLFunctions {

    public static void registerPlugins(Object iRequestor) {
        OSQLEngine.getInstance().registerFunction("dateTimePlusSeconds", new DateTimePlusSecondsFunction());
        OLogManager.instance().info(iRequestor, "dateTimePlusSeconds function registered");
        OSQLEngine.getInstance().registerFunction("pow",  new PowFunction());
        OLogManager.instance().info(iRequestor, "pow function registered");
    }
}
