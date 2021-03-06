<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"\>
			
		<style>

		.button {
		  background-color: #e7e7e7; color: black;
		  border: 2px solid #555555;
		  color: black;
		  padding: 32px 16px;
		  text-align: center;
		  display: inline-block;
		  font-size: 16px;
		  margin: 32px 16px;
		  cursor: pointer;
		  -webkit-transition-duration: 0.4s; /* Safari */
		  transition-duration: 0.4s;
		}

		.button:hover {
		  box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
		}

				
		body {background-color: white}
		h1 {color: black}
		p {color: black}
		</style>

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">

		<a href="/toptrumps/"> <button class="button" >Menu</button></a>

			<div style="text-align:center;">
			<h1> Game Statistics</h1>
			</div>
			

			<div style="text-align:center;">
			<p>Total Games Played</p>
			<p id="gamesPlayed"> </p>
			</div>
			

			<div style="text-align:center;"
			<p>Computer wins</p>
			<p id="cWins"> </p>
			</div>

			<div style="text-align:center;"
			<p>Your wins</p>
			<p id="yWins"> </p>
			</div>

			<div style="text-align:center;"
			<p>Average draws per game</p>
			<p id="draws"> </p>
			</div>

			<div style="text-align:center;"
			<p>Most rounds played</p>
			<p id="maxRounds"> </p>
			</div>
			
			<p id="statsField"> </p>
		
		</div>
		
		<script type="text/javascript">
		
			
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
			
			function initalize(){
			getStats();
			}
		
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function getStats() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getGameStats"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					var JSONstats =  JSON.parse(responseText);
					var gamesPlayed =  JSONstats[0];
					var cWins =  JSONstats[1];
					var yWins = JSONstats[2];
					var draws =  JSONstats[3];
					var maxRounds =  JSONstats[4];
					$("#gamesPlayed").text(gamesPlayed);
					$("#cWins").text(cWins);
					$("#yWins").text(yWins);
					$("#draws").text(draws);
					$("#maxRounds").text(maxRounds);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			

		</script>
		
		</body>
</html>