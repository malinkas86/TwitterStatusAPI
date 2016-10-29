## Twitter Status API

> 1. Clone from the repository

> 2. Extract to a desired location

> 3. in the command line

> 3.1 cd <project folder path> sbt clean compile run

> 4. First get a list of users by

> http://<hostname>:9000/followers?screen_name=<username>

> 5. Use any username to fetch the statuses

> 5.1 Initial request

> http://localhost:9000/statuses?screen_names=<username>&count=10

> The above response will give the next cursor which you have to use to retrieve the next page

> ex : http://<host>:9000/statuses?screen_names=User1,User2&count=10&cursor=276812153
