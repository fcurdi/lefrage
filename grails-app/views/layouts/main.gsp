<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>
  
    <link rel="icon" type="image/png" href="http://developers.mercadolibre.com/wp-content/uploads/logo_puzzle_slider.png" />


    <!--<title>Bare - Start Bootstrap Template</title>-->

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet"  href="${resource(dir: 'css', file: 'bootstrap.min.css', absolute:'true')}">
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

    <asset:javascript src="application.js"/>
  	<asset:stylesheet src="application.css"/>

    <title><g:layoutTitle default="Ima Default Title" /></title>

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'styles.css', absolute:'true')}">
    <style>
	    body{
	    	padding-top: 70px;
	    	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
		}
	</style>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <g:layoutHead/>
</head>

<body>
 <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <span class="navbar-brand" >Lefrage</span>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" style="width:90%;">
                    <Navbar:showLinks/>
                    <li>
                        <g:form controller="search" action="index">
                            <ul class="nav navbar-nav">
                                <li>
                                    <Navbar:search search="${search}"/>
                                </li>
                                <li class="dropdown">
                                    <Navbar:option value="${optionValue}" name="${optionName}"/>
                                </li>   
                            </ul>
                        </g:form>
                    </li>
                     <li class="logout-link">
                        <a href="${createLink(controller:'logout',action:'index')}" style=""> Logout</a>
                    </li>
                    <li class="config-link">
                        <a href="${createLink(controller:'settings',action:'index')}">Configurar Cuenta</a>
                    </li>
                    <li>    
                        <Navbar:showRequests/>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
	<g:layoutBody />
	
    <script type="text/javascript">
    	function changeDropdown(name,value){
        	$("#dropdown").html(name+" <span class='caret'></span>");
            $("#searchOptionValue").val(value);
        	$("#searchOptionName").val(name);
        }
    </script>

</body>

</html>
