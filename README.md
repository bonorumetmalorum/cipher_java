# Java Cipher

### Note to marker:
A known issue with this cipher is its inability
to correctly display characters in Windows 10. This is an issue because
the internal representation of a String in java is encoded in UTF-16. Windows 10
uses as default, a smaller character set than this and so will cause
issues when interacting with the program through cmd or powershell.

I suggest running the jar titled "windows_cipher.jar" which will encrypt then immediately decrypt the
decrypt showing the intermediate stages. Another suggestions is inspecting the output of the tests in the 
log files. This can be accomplished by running `.\gradlew test`

The log files can be found under "logs\\"