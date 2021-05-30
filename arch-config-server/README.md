```
keytool -genkeypair -alias mytestkey -keyalg RSA -dname "CN=Web Server,OU=Unit,O=Organization,L=City,S=State,C=US" -keypass changeme -keystore server.jks -storepass changeme
keytool -importkeystore -srckeystore server.jks -destkeystore server.jks -deststoretype pkcs12
keytool -list -v -keystore server.jks