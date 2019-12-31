## Performance (on Pixel 3)
#### (loop 5000 times on deserialize/serialize the same object and json)
#### deserialize: moshi (58 ms) > gson (68 ms) > fastjson (70 ms) > jackson (395 ms)
#### serialize  : moshi (34 ms) > fastjson (49 ms) > jackson (51 ms) > gson (86 ms)
#### Note: both moshi & jackson support default value of kotlin data class member

#### (with java model)
#### deserialize: gson (55 ms) > moshi (62 ms) > fastjson (71 ms) > jackson (102 ms)
#### serialize  : moshi (49 ms) >= jackson (50 ms) >= fastjson (51 ms) > gson (83 ms)
