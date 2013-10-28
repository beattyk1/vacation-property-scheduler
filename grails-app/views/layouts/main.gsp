<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title><g:layoutTitle default="OPI" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon"
	href="${resource(dir: 'images', file: 'favicon.ico')}"
	type="image/x-icon">
<link rel="apple-touch-icon"
	href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
<link rel="apple-touch-icon" sizes="114x114"
	href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
<r:require modules="bootstrap-responsive-less,bootstrap-js" />
<g:layoutHead />
<r:layoutResources />
</head>
<body>
	<div class="container">
	<h1>Vacation Property Scheduler</h1>

		<div class="navbar">
			<div class="navbar-inner">
				<div class="container">
					<ul class="nav">
						<li class="active"><a href="/vacation">Home</a></li>
						<li><g:link action="create" controller="reservation">Create Reservation Request</g:link></li>
						<li><g:link action="list" controller="reservationDate">Property Availability Calendars</g:link></li>
						<li><g:link action="list" controller="reservation">My Vacation Dates</g:link></li>
						<li><a href="#">See Lottery History</a></li>
						<li><g:link action="list" controller="faq">FAQ</g:link></li>
					</ul>
				</div>
			</div>
		</div>
	<g:layoutBody />
	<div class="footer container" role="contentinfo"></div>
	<div id="spinner" class="spinner" style="display: none;">
		<g:message code="spinner.alt" default="Loading&hellip;" />
	</div>
	<g:javascript library="application" />
	<r:layoutResources />
	</div>
</body>

</html>