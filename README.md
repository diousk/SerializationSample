## Performance (on Pixel 3)
#### (loop 5000 times on deserialize/serialize the same object and json)
#### deserialize: moshi (58 ms) > gson (68 ms) > fastjson (70 ms) > moshi-reflection (203 ms) > jackson (395 ms)
#### serialize  : moshi (34 ms) > fastjson (49 ms) > jackson (51 ms) > moshi-reflection (84 ms) > gson (86 ms)
#### Note: both moshi & jackson support default value of kotlin data class member<br/><br/>

#### (with java model)
#### deserialize: gson (55 ms) > moshi (62 ms) > fastjson (71 ms) > moshi-reflection (98 ms) > jackson (102 ms)
#### serialize  : jackson (47 ms) >= moshi (48 ms) >= fastjson (48 ms) > moshi-reflection (54 ms) > gson (83 ms)
