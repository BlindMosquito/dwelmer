# dwelmer
Similar to screenshot except only has selection method.
You can hit escape to close the program or highlight the area to be coppied to clipboard
I love snipping tool but I hate having to deal with an extra window. 
I miss print screen but I never need to copy everything from a display.

Since the program was created in Java this should work with most operating systems as long as you are current with Java.

You might need Java 17 or newer to run the program

The program jar file is found in dwelmer\out\artifacts\dwelmer_jar\dwelmer.jar

In windows 11 I was able to convert the program to an exe by the following steps
Steps:
  1. In Command Prompt run iexpress:
  2. ![image](https://user-images.githubusercontent.com/2213795/160216332-ece047dc-2525-4938-bea2-6e44c8c29ef7.png)
  3. Click Create new Self Extraction Directive File.
  4. ![image](https://user-images.githubusercontent.com/2213795/160216423-2ec565f0-99f9-44a9-8b66-9951e4b6b2b0.png)
  5. Click Extract Files and run an installation command
  6. ![image](https://user-images.githubusercontent.com/2213795/160216466-baaa4991-c328-4fa1-828d-8ff411c8e608.png)
  7. Give the exe a title ( I used Dwelmer )
  8. ![image](https://user-images.githubusercontent.com/2213795/160216597-16712b1b-fa19-42af-a13d-ba499d5aa90f.png)
  9. Then use No Prompt and Do not display licesnse as options unless you want to be different.
  10. Under packaged files click add then locate the jar file.
  11. The jar file can be found at dwelmer\out\artifacts\dwelmer_jar\dwelmer.jar
  12. ![image](https://user-images.githubusercontent.com/2213795/160216729-60ac2be1-5c98-46cd-b9cb-d07ddd0d5b55.png)
  13. Under Install program write: cmd /K "java -jar dwelmer.jar"
  14. ![image](https://user-images.githubusercontent.com/2213795/160216765-b4087840-6b98-49d2-9750-689329b3cb19.png)
  15. For Show window use Hidden or you will be annoyed by the command prompt.
  16. ![image](https://user-images.githubusercontent.com/2213795/160216786-09f21916-0045-4035-8cbe-0f0e1a771894.png)
  17. For Finished Message use No message
  19.  Under Package Name and Options use Browse and place the exe in the folder of your choosing.
  20.  ![image](https://user-images.githubusercontent.com/2213795/160216892-383f4c97-1f7b-4a19-8fa1-c01d9e92983d.png)
  21.  For Configure Restart I used Only restart if needed
  22.  ![image](https://user-images.githubusercontent.com/2213795/160216923-4cd64143-ab43-417e-a58e-9c6bcf9924b4.png)
  23.  You then get prompted if you would like to save your SED file. I don't believe this is necessary
  24.  Then create package and follow the defaults to finish.

In windows once the exe was created you can run the program from a keyboard macro key. (If you have assignable keys on your keyboard)












