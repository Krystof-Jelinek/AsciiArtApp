Everything should work as it is described on courses with some specifications:

Added command : "--table-non-linear" which then makes the application use the non linear transformation

If user inputs some incorrect command he is notified with error message

with command : "--table "name" " there are 5 predifined tables named "0", "1", "2", "3" and "4" 

command --image-random can have argument which will be the seed of the rng generator so for example "--image-random abcde" is a valid argument

if no image source is specified random image will be generated
(user will be notified since thats in the assignment the program will not fail though)