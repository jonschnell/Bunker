# Bunker Backend Endpoints
v1.0
*Contains: usage and behavior documentation for Endpoints that are marked as completed, in review, or closed.
## usage
##  /user
## **GET**	/user/{id}

return user object given uuid as @PathVariable UUID id.
	
	Example request address:
		coms-309-hv-07.cs.iastate.edu:8080/user/ff428ebd-97c8-4934-951b-3a4963147841
		
	Example JSON Response:
		{
		    "firstName": "Samuel",
		    "lastName": "Jackson",
		    "password": "pigISdirty",
		    "address": "1234 hollywood ave.",
		    "phone": " 776-678-9019",
		    "id": "ff428ebd-97c8-4934-951b-3a4963147841",
		    "user": "SammyJ100",
		    "email": "Sam@jackson.com"
		}

## **GET**	/user/name/{user}
	
return user object given @PathVariable String user.

	Example request address:
		coms-309-hv-07.cs.iastate.edu:8080/user/name/SammyJ100
	
	Example JSON Response:
		{
		    "firstName": "Samuel",
		    "lastName": "Jackson",
		    "password": "pigISdirty",
		    "address": "1234 hollywood ave.",
		    "phone": " 776-678-9019",
		    "id": "ff428ebd-97c8-4934-951b-3a4963147841",
		    "user": "SammyJ100",
		    "email": "Sam@jackson.com"
		}
## **POST**	/user
create new user @RequestBody user user

	Example Request body:
		{
		    "firstName": "Samuel",
		    "lastName": "Jackson",
		    "password": "pigISdirty",
		    "address": "1234 hollywood ave.",
		    "phone": " 776-678-9019",
		    "user": "SammyJ100",
		    "email": "Sam@jackson.com"
		}
	
	Example Response:
		{
		    "id": "ff428ebd-97c8-4934-951b-3a4963147841",
		    "user": "SammyJ100",
		    "address": "1234 hollywood ave.",
		    "firstName": "Samuel",
		    "lastName": "Jackson",
		    "email": "Sam@jackson.com",
		    "phone": " 776-678-9019",
		    "password": "pigISdirty",
		    "credentials": null
		}
## **POST**	/user/login
return user id if presented user object with correct username and password @RequestBody HashMap u (where hashmap u is a object with fields user and password.

	Example Request body:
			{
			    "userName": "SammyJ100",
			    "password": "pigISdirty"
			}
			
	Example SUCESSFUL response:
			{
			    "id": "ff428ebd-97c8-4934-951b-3a4963147841",
			    "status": "1"
			}
			
	Example FAILED BAD PASSWORD response:
			{
			    "status": "0"
			}
			
	Example FAILED BAD USERNAME response:
			{
			    "timestamp": 1602557365951,
			    "status": 500,
			    "error": "Internal Server Error",
			    "exception": "java.lang.NullPointerException",
			    "message": "No message available",
			    "path": "/user/login"
			}
## **PUT**	/user/{id}
Return newly modified user when presented @RequestBody user u & @PathVariable UUID id. everything but the user's UUID is update able. **all fields must be present even if they are not being updated!**

	Example request address:
		coms-309-hv-07.cs.iastate.edu:8080/user/ff428ebd-97c8-4934-951b-3a4963147841
	
	Example request body
		{
		    "firstName": "Samuel",
		    "lastName": "Jackson",
		    "password": "NewPassword",
		    "address": "1234 hollywood ave.",
		    "phone": " 776-678-9019",
		    "user": "SammyJ100",
		    "email": "Sam@jackson.com"
		}
		
**responses with new user object only enabled for testing**
## **DELETE**	/user/{id}

deletes user data and credential data of user with @PathVariable UUID id.

	Example request Address:
		coms-309-hv-07.cs.iastate.edu:8080/user/ff428ebd-97c8-4934-951b-3a4963147841
		
	Example sucessful response:
		{
		    "status": "1"
		}
	Example Failed response:
		{
		    "status: "0"
		}



#  /credential
## **POST**	/credential/{id}
store new credential @RequestBody credential c & @PathVariable UUID id

	Example request Address:
		coms-309-hv-07.cs.iastate.edu:8080/credential/ff428ebd-97c8-4934-951b-3a4963147841
		
	Example request body
		{
		    "credentialName": "hulu",
		    "userName": "huluUserName",
		    "password": "huluPassword"
		}
		
	Example Response body:
		{
		    "owner": "ff428ebd-97c8-4934-951b-3a4963147841",
		    "password": "huluPassword",
		    "id": "e25f1a82-ba19-4d0a-aad9-8910909ed9fe",
		    "credentialName": "hulu",
		    "userName": "huluUserName"
		}
## **GET**	/credential/getAll/{id}
returns list of credential objects owned by user @PathVariable UUID id

	Example request Address:
		coms-309-hv-07.cs.iastate.edu:8080/credential/getAll/ff428ebd-97c8-4934-951b-3a4963147841
		
	Example Response body:
		{
			"credentials" : [
		        	{
		            	"owner": "ff428ebd-97c8-4934-951b-3a4963147841",
		            	"password": "netflixPassword",
		            	"id": "b81b7430-eb56-4b13-99cd-528a130c57b8",
		            	"credentialName": "netflix",
		            	"userName": "netflixUserName"
		        	},
		        	{
		        	    "owner": "ff428ebd-97c8-4934-951b-3a4963147841",
		        	    "password": "huluPassword",
		        	    "id": "e867498c-28cb-4451-90ba-5237a75741af",
		        	    "credentialName": "hulu",
		      		    "userName": "huluUserName"
		       		}
		    ]
		}
## **GET**	/credential/getAllId/{id}
returns json object with a field called credentials mapped to a java linked list of credential ID's @PathVariable UUID id

	Example request Address:
		coms-309-hv-07.cs.iastate.edu:8080/credential/getallId/ff428ebd-97c8-4934-951b-3a4963147841
		
	Example Response body:
		{
    			"credentials": [
      			  "b81b7430-eb56-4b13-99cd-528a130c57b8",
      			  "e867498c-28cb-4451-90ba-5237a75741af"
   			 ]
		}
#  /friend
## **POST**	/friend/{id}
store new friend @RequestBody friend f & @PathVariable UUID id

	Example request Address:
		coms-309-hv-07.cs.iastate.edu:8080/friend/0897f804-0a64-4ee3-a52c-038cb2256f27
		
	Example request body
		{
		    "friendName": "notIsabel"
		}
		
	Example Response body:
		{
		    "owner": "ff428ebd-97c8-4934-951b-3a4963147841",
		    "friendName": "notIsabel",
		    "id": "4daa5a18-9730-4ef1-af39-cea4f8ee2dd9"
		}
## **GET**	/friend/getAll/{id}
returns list of friend objects owned by user @PathVariable UUID id

	Example request Address:
		coms-309-hv-07.cs.iastate.edu:8080/friend/getAll/0897f804-0a64-4ee3-a52c-038cb2256f27
		
	Example Response body:
        [
            {
                "owner": "0897f804-0a64-4ee3-a52c-038cb2256f27",
                "friendName": "notIsabelAgain",
                "id": "a327db64-a102-4994-8591-3e308eb86b3d"
            },
            {
                "owner": "0897f804-0a64-4ee3-a52c-038cb2256f27",
                "friendName": "notIsabel",
                "id": "aa001e46-a998-4c1e-9eef-ea8bb001e25b"
            },
            {
                "owner": "0897f804-0a64-4ee3-a52c-038cb2256f27",
                "friendName": "alsoNotIsabel",
                "id": "ed878530-9f6f-40ae-b488-f2e766c1cf68"
            }
        ]

## **GET**	/friend/getAllId/{id}
returns json object with a field called friends mapped to a java linked list of friend IDs @PathVariable UUID id

	Example request Address:
		coms-309-hv-07.cs.iastate.edu:8080/friend/getallId/ff428ebd-97c8-4934-951b-3a4963147841
		
	Example Response body:
	"friend": [
            "a327db64-a102-4994-8591-3e308eb86b3d",
            "aa001e46-a998-4c1e-9eef-ea8bb001e25b",
            "ed878530-9f6f-40ae-b488-f2e766c1cf68"
        ]
		

