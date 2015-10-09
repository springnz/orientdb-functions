package springnz.orientdb.plugin;

import com.orientechnologies.orient.server.OServer;
import com.orientechnologies.orient.server.config.OServerParameterConfiguration;
import com.orientechnologies.orient.server.plugin.OServerPluginAbstract;

public class OYlabsFunctionsPlugin extends OServerPluginAbstract {

    public OYlabsFunctionsPlugin() {
    }

    public String getName() {
        return "springnz-functions-plugin";
    }

    @Override
    public void startup() {
        super.startup();
        OSQLFunctions.registerPlugins(this);
    }

    @Override
    public void config(OServer oServer, OServerParameterConfiguration[] iParams) {

    }

    @Override
    public void shutdown() {
        super.shutdown();
    }
}
