# API endpoints

These endpoints allow you to access My Movie Memoir services.

## Resource

My Movie Memoir supports the following list of resource.

* [person](#moviememoirwsperson)
* [cinema](#moviememoirwscinema)
* [credentials](#moviememoirwscredentials)
* [memoir](#moviememoirwsmemoir)

These can be used alone as follow:


| Resource                     | Description                       |
| :----------------------------- | ----------------------------------- |
| `/moviememoirws.person`      | Returns a list of all users       |
| `/moviememoirws.cinema`      | Returns a list of all cinemas     |
| `/moviememoirws.credentials` | Returns a list of all credentials |
| `/moviememoirws.memoir`      | Returns a list of all memoirs     |

## Resource identifiers

Resource can be used in conjunction with identifier to retrieve metadata for the specified identifier.


| Resource                         | Description                                            |
| :--------------------------------- | -------------------------------------------------------- |
| `/moviememoirws.person/{id}`     | Returns user metadata for the specified user id        |
| `/moviememoirws.cinema/{id}`     | Returns cinema metadata for the specified cinema id    |
| `/moviememoirws.credential/{id}` | Returns credentials metadata for the specified user id |
| `/moviememoirws.memoir/{id}`     | Returns memoir metadata for the specified memoir id    |

## Resource components

Resource can be used in conjunction with components to retrieve metadata for searching purposes.

Each resource has its list of suppoeted components.

## **/moviememoirws.person/**


| Components                                             | Description                                                                                                                             |
| :------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------- |
| `/findByFirstName/{fname}`                             | Returns a list of user metadata for the specified user firsname                                                                         |
| `/findBySurname/{surname}`                             | Returns a list of user metadata for the specified user surname                                                                          |
| `/findByStateCode/{stateCode}`                         | Returns a list of user metadata for the specified state code (eg. VIC, NSW, WA, SA, etc). Currently only support Australian state codes |
| `/findByStreetNumber/{streetNumber}`                   | Returns a list of user metadata for the specified street number                                                                         |
| `/findByStreetName/{streetName}`                       | Returns a list of user metadata for the specified street name                                                                           |
| `/findByGender/{gender}`                               | Returns a list of user metadata for the specified gender (M or F)                                                                       |
| `/findByPostcode/{postcode}`                           | Returns a list of user metadata for the specified postcode                                                                              |
| `/findByDob/{dob}`                                     | Returns a list of user metadata for the specified date of birth                                                                         |
| `/findByFullNamePostcode/{fname}/{surname}/{postcode}` | Returns a list of user metadata for the specified full name                                                                             |

## **/moviememoirws.cinema/**


| Components                       | Description                                                     |
| :--------------------------------- | ----------------------------------------------------------------- |
| `/findByCinemaName/{cinemaName}` | Returns a list of cinema metadata for the specified cinema name |
| `/findBySuburb/{suburb}`         | Returns a list of cinema metadata for the specified suburb      |

## **/moviememoirws.credentials/**


| Components                                    | Description                                                                   |
| :---------------------------------------------- | ------------------------------------------------------------------------------- |
| `/findByUsername/{username}`                  | Returns a list of credential metadata for the specified username              |
| `/findByPassword/{password}`                  | Returns a list of credential metadata for the specified password              |
| `/findByAuthentication/{username}/{password}` | Returns a list of credential metadata for the specified username and password |
| `/findBySignUpdate/{signUpDate}`              | Returns a list of credential metadata for the specified signup date           |

## **/moviememoirws.memoir/**


| Components                                             | Description                                                                                                    |
| :------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------- |
| `/findByWatchDate/{watchDate}`                         | Returns a list of memoir metadata for the specified watch date firsname                                        |
| `/findByMovieNameAndCinema/{movieName}/{cinemaName}`   | Returns a list of memoir metadata for the specified movien name and cinema name surname                        |
| `/topFiveMovieOfTheYear/{userId}`                      | Returns a list of memoir metadata for the specified user id                                                    |
| `/findByIdDate/{userId}/{startDate}/{endDate}`         | Returns a list of memoir metadata for the specified user id, range of watch date - start date and end date     |
| `/findRemakebyUserID/{userId}`                         | Returns a list of memoir metadata of remake version movies watched by the user with the specified user id name |
| `/findByWatchTime/{watchTime}`                         | Returns a list of memoir metadata for the specified watch time                                                 |
| `/findByReleaseDate/{releaseDate}`                     | Returns a list of memoir metadata for the specified movie release date                                         |
| `/findByComment/{comment}`                             | Returns a list of memoir metadata for the specified comment                                                    |
| `/countMoviePerMonth/{userId}/{year}`                  | Return the counted the number of watched movie per month for the user with the specified user id               |
| `/findByMovieNameAndCinemaSuburb/{movieName}/{suburb}` | Returns a list of memoir metadata for the specified movie name and cinema suburb                               |
| `/findHigestRatingbyUserid/{userId}`                   | Returns a list of memoir metadata with the highest rating for the user with the specified user id              |
| `/findByUID/{userId}`                                  | Returns a list of memoir metadata for the specified user id                                                    |
| `/findByMovieName/{movieName}`                         | Returns a list of memoir metadata for the specified movie name                                                 |
| `/findByRating/{rating}`                               | Returns a list of memoir metadata for the specified rating                                                     |
