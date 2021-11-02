# Mutant
java code for entry test

# Dependencies:
Spring Boot framework

# Java Version:
JDK1.8.0_77

# Business logic

# Horizontal Sequences
To find the horizontal sequences, at each position of the arrangement that comes in the request, the valid sequences were searched

# Vertical Sequences
To find the vertical sequences, the arrangement of strings was converted into an arrangement of characters and the corresponding character was obtained, supplying the position of each member of the column, converting the columns into rows and using the method that validates the horizontal sequences, it was validated those that met the requirement

# Diagonal Sequences
To find the diagonal sequences, the character arrangement was traversed and the corresponding positions of the diagonal were validated if it met the requirement

# Run the api 
from postman with the url http://mutantapp-env.eba-uwm9ppen.us-east-2.elasticbeanstalk.com/mutant/, with a POST request, start a json with the following format:
{
"dna": ["AGGCGA", "CAGTGC", "TTAGGT", "AGAAGG", "CCCTTA", "TCACTG"]
}
