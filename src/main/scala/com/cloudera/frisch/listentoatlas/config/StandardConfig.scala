package com.cloudera.frisch.listentoatlas.config

object StandardConfig extends AppConfig {

  val appName = appConf.getString("name")
  val kerberosUser = appConf.getString("kerberos-user")
  val keytab = appConf.getString("keytab")

}
