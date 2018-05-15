<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1 ">

<!-- Bootstrap CSS 4.0.0 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/escritorio/css/marco.css">

<!-- Font Awesome CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script src="${pageContext.request.contextPath}/scripts/navegacion.js"></script>

<script>
    window.history.pushState({"html":window.location.pathname,"titulo":$(document).attr('title')}, "", window.location.pathname);
</script>