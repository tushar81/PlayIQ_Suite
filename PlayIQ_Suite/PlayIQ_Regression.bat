set projectLocation=C:\Users\ukccs1tsa\Selenium\PlayIQ_Suite
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java -cp %classpath% org.testng.TestNG %projectLocation%\PlayIQ_Regression.xml
pause