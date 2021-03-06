# API endpoints

These endpoints allow you to access My Movie Memoir services.

## Resource

My Movie Memoir supports the following list of resource.

* [person](#moviememoirwsperson) - Holds all person metadata excluding credentials
* [cinema](#moviememoirwscinema) - Holds all cinema metadata including cinema name and suburb
* [credentials](#moviememoirwscredentials) - Holds all person credential metadata
* [memoir](#moviememoirwsmemoir) - Holds all memoir metadata

These can be used alone as follow:


| Resource                     | Description                       |
| :----------------------------- | ----------------------------------- |
| `/moviememoirws.person`      | Returns a list of all users       |
| `/moviememoirws.cinema`      | Returns a list of all cinemas     |
| `/moviememoirws.credentials` | Returns a list of all credentials |
| `/moviememoirws.memoir`      | Returns a list of all memoirs     |

## Resource identifiers

Resource can be used in conjunction with identifier to retrieve metadata for the specified identifier.


| Resource                         | Description                                              |
| :--------------------------------- | ---------------------------------------------------------- |
| `/moviememoirws.person/{id}`     | Returns person metadata for the specified person id      |
| `/moviememoirws.cinema/{id}`     | Returns cinema metadata for the specified cinema id      |
| `/moviememoirws.credential/{id}` | Returns credentials metadata for the specified person id |
| `/moviememoirws.memoir/{id}`     | Returns memoir metadata for the specified memoir id      |

## Resource components

Resource can be used in conjunction with components to retrieve metadata for searching purposes.

Each resource has its list of suppoeted components.

## **/moviememoirws.person/**


| Components                                             | Description                                                                                                                               |
| :------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------- |
| `/findByFirstName/{fname}`                             | Returns a list of person metadata for the specified person firsname                                                                       |
| `/findBySurname/{surname}`                             | Returns a list of person metadata for the specified person surname                                                                        |
| `/findByStateCode/{stateCode}`                         | Returns a list of person metadata for the specified state code (eg. VIC, NSW, WA, SA, etc). Currently only support Australian state codes |
| `/findByStreetNumber/{streetAddress}`                  | Returns a list of person metadata for the specified street address                                                                        |
| `/findByGender/{gender}`                               | Returns a list of person metadata for the specified gender (M or F)                                                                       |
| `/findByPostcode/{postcode}`                           | Returns a list of person metadata for the specified postcode                                                                              |
| `/findByDob/{dob}`                                     | Returns a list of person metadata for the specified date of birth                                                                         |
| `/findByFullNamePostcode/{fname}/{surname}/{postcode}` | Returns a list of person metadata for the specified full name                                                                             |

## **/moviememoirws.cinema/**


| Components                       | Description                                                     |
| :--------------------------------- | ----------------------------------------------------------------- |
| `/findByCinemaName/{cinemaName}` | Returns a list of cinema metadata for the specified cinema name |
| `/findByPostcode/{postcode}`     | Returns a list of cinema metadata for the specified postcode    |

## **/moviememoirws.credentials/**


| Components                                    | Description                                                                   |
| :---------------------------------------------- | ------------------------------------------------------------------------------- |
| `/findByAuthentication/{username}/{password}` | Returns a list of credential metadata for the specified username and password |
| `/findByUsername/{username}`                  | Returns a list of credential metadata for the specified username              |
| `/findBySignUpdate/{signUpDate}`              | Returns a list of credential metadata for the specified signup date           |

## **/moviememoirws.memoir/**


| Components                                                 | Description                                                                                                      |
| :----------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| `/countMoviePerMonth/{userId}/{year}`                      | Return the counted the number of watched movie per month for the person with the specified person id             |
| `/findAll`                                                 | Return a list of all memoir metadata in the system                                                               |
| `/countByIdDatePerPostcode/{userId}/{startDate}/{endDate}` | Count the number of entry per postcode for the specified person id between the specified start date and end date |
| `/findByMovieName/{movieName}`                             | Returns a list of memoir metadata for the specified movie name                                                   |
| `/findByMovieNameAndCinema/{movieName}/{cinemaName}`       | Returns a list of memoir metadata for the specified movien name and cinema name surname                          |
| `/findByMovieNameAndCinemaPostcode/{movieName}/{postcode}` | Returns a list of memoir metadata for the specified movie name and cinema postcode                               |
| `/findByRating/{rating}`                                   | Returns a list of memoir metadata for the specified rating                                                       |
| `/findByReleaseDate/{releaseDate}`                         | Returns a list of memoir metadata for the specified movie release date                                           |
| `/findByUID/{userId}`                                      | Returns a list of memoir metadata for the specified person id                                                    |
| `/findByWatchDate/{watchDate}`                             | Returns a list of memoir metadata for the specified watch date                                                   |
| `/findHigestRatingbyUserid/{userId}`                       | Returns a list of memoir metadata with the highest rating for the person with the specified person id            |
| `/topFiveMovieOfTheYear/{userId}`                          | Returns a list of memoir metadata for the specified person id                                                    |
