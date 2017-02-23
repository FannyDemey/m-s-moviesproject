# m-s-moviesproject

## Getting Started

### Start the application : 

#### Start a mongodb locally

you must start a mongodb on your computer to run this app. it must start on localhost and on port 27017. If you want to change thos parameters, please modify application.properties file in consequence located in resources folder.


#### Run the application

* if you are using IntelliJ :
just press Alt + Shift + F10

* using maven : 
```bash
mvn package
mvn spring-boot:run
```

### Test coverage

The class MovieController cover nominal test case using JUnit
you don't need to run a mongo db server locally to run it.

### Play with it

Now that your application is started, you can go to http://localhost:3000/ and start using the API

#### Some curl example :

* Add some movies to the database : 
```bash
curl -X POST -H "Content-Type: application/json" -d '{  	"id":50,    "name": "Love actually", "genre": "Drama",    "year": "2003",    "available":"true"}' "http://localhost:3000/movie"
curl -X POST -H "Content-Type: application/json" -d '{  "id":60,    "name": "Scoop", "genre": "Drama",    "year": "2006",    "available":"true"}' "http://localhost:3000/movie"
curl -X POST -H "Content-Type: application/json" -d '{  	"id":60,    "name": "Seven", "genre": "Thriller",    "year": "1995",    "available":"true"}' "http://localhost:3000/movie"
curl -X POST -H "Content-Type: application/json" -d '{  	"id":70,    "name": "Forrest Gump", "genre": "Drama",    "year": "1994",    "available":"true"}' "http://localhost:3000/movie"
curl -X POST -H "Content-Type: application/json" -d '{  	"id":80,    "name": "Paris", "genre": "Drama",    "year": "2008",    "available":"true"}' "http://localhost:3000/movie"
curl -X POST -H "Content-Type: application/json"  -d '{  	"id":90,    "name": "Fight club", "genre": "Drama",    "year": "1999",    "available":"true"}' "http://localhost:3000/movie"
curl -X POST -H "Content-Type: application/json"  -d '{  	"id":100,    "name": "Very bad trip", "genre": "Comedy",    "year": "2008",    "available":"true"}' "http://localhost:3000/movie"
```


* Add users 
```bash
curl -X POST -H "Content-Type: application/json" -d '{"id" :2, "email" : "fanny.pluvinage@gmail.com","type" : "regular"}' "http://localhost:3000/user"
curl -X POST -H "Content-Type: application/json" -d '{"id" :3, "email" : "sundar.pichai@gmail.com","type" : "registered"}' "http://localhost:3000/user"
```

* Find by Genre 
```bash
curl -X GET "http://localhost:3000/movie/search/findByGenre?genre=Drama"
```

* Find by Year 
```bash
curl -X GET "http://localhost:3000/movie/search/findByYear?year=Drama"
```

* Rent a movie 
```bash
curl -X PUT -H "Content-Type: application/json" -d '{"id" : 3,"email" : "sundar.pichai@gmail.com","type" : "registered"}' "http://localhost:3000/movies/Seven"
curl -X PUT -H "Content-Type: application/json" -d '{"id" : 2,"email" : "fanny.pluvinage@gmail.com","type" : "regular"}' "http://localhost:3000/movies/Love%20actually"
```

* List rented movies
```bash
curl -X GET "http://localhost:3000/movies"
```
