package ylabs.orientdb.plugin;

import com.orientechnologies.common.log.OLogManager;
import com.orientechnologies.orient.core.sql.OSQLEngine;
import com.orientechnologies.orient.server.OServer;
import com.orientechnologies.orient.server.config.OServerParameterConfiguration;
import com.orientechnologies.orient.server.plugin.OServerPluginAbstract;

public class OYlabsFunctionsPlugin extends OServerPluginAbstract {

    public OYlabsFunctionsPlugin() {
    }

    public String getName() {
        return "ylabs-functions-plugin";
    }

    @Override
    public void startup() {
        super.startup();
        OSQLEngine.getInstance().registerFunction("dateTimePlusSeconds", OSQLFunctions.dateTimeAddSecondsFunction());
        OLogManager.instance().info(this, "dateTimePlusSeconds function registered");
    }

    @Override
    public void config(OServer oServer, OServerParameterConfiguration[] iParams) {

    }

    @Override
    public void shutdown() {
        super.shutdown();
    }
}
