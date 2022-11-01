# assignment-nuchange
To run this program use command line as:- javac NuchangeAssignment.java ⏎ java NuchangeAssignment ⏎
then you get Commands: storeurl <url>
                       get <url>
                       count<url> 
                       list 
                       exit
you can get any of them or all of them result after providing the required details like ex:- (storeurl google.com) them it will be saved through
key-value pair in hashMap .The command get shall take a URL as a parameter(get <url>) and return the unique short key after incrementing the usage count.
By get <url> the count variable increased first then it return unique sort key.The command count shall take a URL as a parameter and should return
the latest usage count. if you want to list all url then give list to command line it returns (url : count) pair in JSON format.
and to terminate the program enter exit and program stops. 
CODE:
first I have load all util package in the NuchangeAssignment class to get access of diffrent classes. then Scanner class to take user input,
after that i have made a hashtable urlDetails(8) which stores url as key and string array of unique sort key of url and count variable as value.
after main method  In line 12 I have run a while loop so that until exit command it always demands input.After that
printCommand()(13) before taking input it prints on console asCommands:
storeurl <URL>
get <URL>
count <URL>
list
exit
through line (38 -48) then taking  input in the form of string which is checked by isValidCommand() which takes input as input variable and gives 
boolean result (50-64) in this method it checks if input is "exit" or "list" returns true otherwise split string by space into string array then 
check size of string array if size[] != 2 then it gives false then if string [] contains at 0 index as "storeurl" or "get" or "count" it return true 
otherwise returns false. if isValidCommand method gives false as output it return as error then continue which again takes user input through command line
then after that again check input.
if input start with storeurl then storeurl(input) method (68-77) called which first split input string then check if urlDetails contained that value 
already or not if hashmap contains that key then gives output as url aready exists if not then first make unique key of string type for url through
encode method as in (74) and then count variable created and initialized as 0. then stores url as key and that unique key of url and count in value 
of hashmap as string array .
If input starts with get keyword then geturlSortKey(input) method called which first check if input url is present or not in urlDetails if not present 
it gives output as url doesn't exits like otherwise it gives the unique value which is stored in hashmap urlDetails (79-88).
then if inut mtaches with "count url" then getCount(input ) called which first check if input url is present or not in urlDetails if not present 
it gives output as erroe url doesn't exits otherwise gives count details from urlDetails(90-97).
If input matches with "list" then it calls listUrl() method (99-108) which first check if urlDetails is empty or not if empty it gives output as url 
is empty and return. otherwise list url and count value from urlDetails in JSON format by Iterating over urlDetails hashmap.
if input mathes with "exit" then System.exit(0) is called which terminates the program.
