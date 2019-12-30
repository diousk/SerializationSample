## Performance 
#### (loop 5000 times on deserialize/serialize the same object and json)
#### deserialize: moshi (58 ms) > gson (68 ms) > fastjson (70 ms) > jackson (395 ms)
#### serialize  : moshi (34 ms) > fastjson (49 ms) > jackson (51 ms) > gson (86 ms)
#### Note: both moshi & jackson support default value of data class member
