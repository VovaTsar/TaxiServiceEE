
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exception</title>
</head>
<body>
<div class="error">
    <img src="image/error.png" alt="Error"/>
    <h3>Oops.. Some error occurred :(</h3>
</div>

<style>
    body {
        margin: 0;
    }
    .error{
        min-height: 100vh;
        width: 100vw;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }
    .error img {
        width: 80%;
        max-width: 300px;
    }
    .error h3 {
        margin-top: 20px;
        font-size: 2rem;
        color: #333;
    }
</style>

</body>
</html>