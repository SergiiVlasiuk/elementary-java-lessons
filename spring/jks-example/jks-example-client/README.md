## $ keytool -genkey -alias MyClientAlias -keyalg RSA -validity 1825 -keystore "MyClient.jks" 

Enter keystore password:  password
Re-enter new password: password
What is your first and last name?
  [Unknown]:  sergii client
What is the name of your organizational unit?
  [Unknown]:  test jks from client
What is the name of your organization?
  [Unknown]:  training
What is the name of your City or Locality?
  [Unknown]:  poltava
What is the name of your State or Province?
  [Unknown]:  ukraine
What is the two-letter country code for this unit?
  [Unknown]:  UA
Is CN=sergii client, OU=test jks from client, O=training, L=poltava, ST=ukraine, C=UA correct?
  [no]:  yes

Enter key password for <MyClientAlias>
        (RETURN if same as keystore password):  clientPassword
Re-enter new password: clientPassword

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore MyClient.jks -destkeystore MyClient.jks -deststoretype pkcs12".


## $ keytool -list -keystore ./MyClient.jks 

Enter keystore password:  password
Keystore type: jks
Keystore provider: SUN

Your keystore contains 1 entry

myclientalias, Feb 22, 2019, PrivateKeyEntry, 
Certificate fingerprint (SHA1): A5:A3:2F:AF:CB:C0:23:87:26:E8:71:01:8D:5C:68:97:E5:52:63:9D

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore ./MyClient.jks -destkeystore ./MyClient.jks -deststoretype pkcs12".

## $ keytool -exportcert -alias MyClientAlias -keystore MyClient.jks -file MyClient.cer
Enter keystore password:  password
Certificate stored in file <MyClient.cer>

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore MyClient.jks -destkeystore MyClient.jks -deststoretype pkcs12".


# --Add Server certificate to client truststore
## server certificate should be generated

## $ keytool -importcert -alias MyServer3Alias -keystore MyClient.jks -file ../../../../ssl-server/src/main/resources/MyServer3.cer

Enter keystore password: password  
Owner: CN=sergii vlasiuk, OU=jks testing, O=personal training, L=poltava, ST=ukraine, C=UA
Issuer: CN=sergii vlasiuk, OU=jks testing, O=personal training, L=poltava, ST=ukraine, C=UA
Serial number: 54fe2f3e
Valid from: Fri Feb 22 01:03:11 EET 2019 until: Wed Feb 21 01:03:11 EET 2024
Certificate fingerprints:
         MD5:  FE:03:7F:62:83:1B:62:B6:3E:04:B0:93:04:53:12:23
         SHA1: 52:22:0D:E6:E1:3F:F7:6A:62:59:A2:DA:13:61:03:58:8C:33:83:AA
         SHA256: 56:7B:C4:E7:A6:6B:C6:88:87:AF:CF:8F:7D:D2:D7:99:1F:E6:25:A4:CA:2D:CA:E1:ED:F0:AE:D7:5F:98:73:37
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 3

Extensions: 

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: 88 5D DE C2 D3 83 42 00   33 79 65 AF 4E 46 69 66  .]....B.3ye.NFif
0010: C6 AD A8 03                                        ....
]
]

Trust this certificate? [no]:  yes
Certificate was added to keystore

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore MyClient.jks -destkeystore MyClient.jks -deststoretype pkcs12".
