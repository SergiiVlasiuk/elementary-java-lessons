## Request examples:

#### examples with dto endpoint

 - `curl "http://localhost:8080/verify/criteria/mapping/community/123/dto"`
 - `curl "http://localhost:8080/verify/criteria/mapping/community/123/dto?till=2019-05-13&communityName=myCOm"`
 - `curl "http://localhost:8080/verify/criteria/mapping/community/123/dto?from=2018-11-14&till=2019-05-13&communityName=myCOm"`

#### examples with usual endpoint

 - `curl "http://localhost:8080/verify/criteria/mapping/community/123/default/params"`
 - `curl "http://localhost:8080/verify/criteria/mapping/community/123/default/params?till=2019-05-13&communityName=myCOm"`
 - `curl "http://localhost:8080/verify/criteria/mapping/community/123/default/params?from=2018-11-14&till=2019-05-13&communityName=myCOm"`

#### examples with spring out of the box converting endpoint

 - `curl "http://localhost:8080/verify/criteria/mapping/community/123/converted/params?from=2018-11-14&till=2019-05-13"`
 - `curl "http://localhost:8080/verify/criteria/mapping/community/123/converted/params?from=2018-11-14&till=2019-05-13&communityName=myCOm"`

## Related question on this commit:

 - [stack overflow question](https://stackoverflow.com/q/58544601/5728095)

#### examples with dto endpoint - solution

 - `curl "http://localhost:8080/verify/criteria/mapping/community/123/dtold"`
 - `curl "http://localhost:8080/verify/criteria/mapping/community/123/dtold?till=2019-05-13&communityName=myCOm"`
 - `curl "http://localhost:8080/verify/criteria/mapping/community/123/dtold?from=2018-11-14&till=2019-05-13&communityName=myCOm"`
