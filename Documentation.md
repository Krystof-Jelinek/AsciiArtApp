Everything should work as it is described on courses with some specifications:

Added command : "--table-non-linear" which then makes the application use the non linear transformation

If user inputs some incorrect command he is notified with error message

with command : "--table "name" " there are 5 predifined tables named "0", "1", "2", "3" and "4" 

command --image-random can have argument which will be the seed of the rng generator so for example "--image-random abcde" is a valid argument

added special command --image-random-online which i added just for fun and as a test if the code is easily expandable -- this command can accept seed as argument and
it downloads some random image from internet and uses it in the transformation (this was originally not my idea, got inspired by one of my friends)