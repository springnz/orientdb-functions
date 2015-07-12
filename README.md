# Custom SQL functions for OrientDB.

Currently one function is implemented:

* `dateTimePlusSeconds`. Add seconds to a timestamp: `dateTimePlusSeconds <datetime> <seconds>`

Deployment
==========

* Configure [Maven credentials](https://springdom.atlassian.net/wiki/display/SD/Maven+settings+for+Nexus) for Nexus
* `mvn deploy`

Installation on remote instance
===============================

Copy `orientdb-functions-plugin-<version>.jar` to `<orientdb-install-dir>/plugins/`.

On server startup you should see the following
```
2015-07-13 09:34:15:289 INFO  Installing dynamic plugin 'orientdb-functions-plugin-1.0.1.jar'... [OServerPluginManager]
2015-07-13 09:34:15:334 INFO  dateTimePlusSeconds function registered [OYlabsFunctionsPlugin]
```

Installation on embedded instance
=================================

1. Add dependency to project: `"ylabs" % "orientdb-functions-plugin" % "1.0.1",`
2. Register functions with embedded server `OSQLFunctions.registerPlugins(this)`
3. Use it. E.g., `select dateTimePlusSeconds(date("2015-07-10 12:00:00"), 30)`
