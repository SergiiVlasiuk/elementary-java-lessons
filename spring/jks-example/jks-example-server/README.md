## $ keytool -genkey -alias MyServer3Alias -keyalg RSA -validity 1825 -keystore "MyServer3.jks" 

Enter keystore password:  password
Re-enter new password: password
What is your first and last name?
  [Unknown]:  sergii vlasiuk
What is the name of your organizational unit?
  [Unknown]:  jks testing
What is the name of your organization?
  [Unknown]:  personal training
What is the name of your City or Locality?
  [Unknown]:  poltava
What is the name of your State or Province?
  [Unknown]:  ukraine
What is the two-letter country code for this unit?
  [Unknown]:  UA
Is CN=sergii vlasiuk, OU=jks testing, O=personal training, L=poltava, ST=ukraine, C=UA correct?
  [no]:  yes

Enter key password for <MyServer3Alias>
        (RETURN if same as keystore password): serverPassword  
Re-enter new password: serverPassword

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore MyServer3.jks -destkeystore MyServer3.jks -deststoretype pkcs12".


## $ keytool -list -keystore ./MyServer3.jks 

Enter keystore password:  password
Keystore type: jks
Keystore provider: SUN

Your keystore contains 1 entry

myserver3alias, Feb 22, 2019, PrivateKeyEntry, 
Certificate fingerprint (SHA1): 52:22:0D:E6:E1:3F:F7:6A:62:59:A2:DA:13:61:03:58:8C:33:83:AA

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore ./MyServer3.jks -destkeystore ./MyServer3.jks -deststoretype pkcs12".

## $ keytool -exportcert -alias MyServer3Alias -keystore MyServer3.jks -file MyServer3.cer
Enter keystore password:  password
Certificate stored in file <MyServer3.cer>

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore MyServer3.jks -destkeystore MyServer3.jks -deststoretype pkcs12".


# --Add client certificate to server truststore
## client certificate should be generated
## $ keytool -importcert -alias MyClientAlias -keystore MyServer3.jks -file ../../../../ssl-client/src/main/resources/MyClient.cer

Enter keystore password: password  
Owner: CN=sergii client, OU=test jks from client, O=training, L=poltava, ST=ukraine, C=UA
Issuer: CN=sergii client, OU=test jks from client, O=training, L=poltava, ST=ukraine, C=UA
Serial number: 73e5cccb
Valid from: Fri Feb 22 01:42:28 EET 2019 until: Wed Feb 21 01:42:28 EET 2024
Certificate fingerprints:
         MD5:  FD:92:76:73:BD:6C:D3:92:6E:6E:D0:0D:57:0C:83:D9
         SHA1: A5:A3:2F:AF:CB:C0:23:87:26:E8:71:01:8D:5C:68:97:E5:52:63:9D
         SHA256: E8:B4:4C:B9:C3:A9:66:70:1D:18:E0:7F:B9:6B:5B:37:E7:1B:AF:20:A6:59:1F:FA:54:AC:CC:CF:70:92:83:D6
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 3

Extensions: 

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: 4F 2B 88 A2 A5 25 7E 36   CC 5B 0D 82 F3 B2 28 B3  O+...%.6.[....(.
0010: FF 28 A2 88                                        .(..
]
]

Trust this certificate? [no]:  yes
Certificate was added to keystore

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore MyServer3.jks -destkeystore MyServer3.jks -deststoretype pkcs12".
