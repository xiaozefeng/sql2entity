<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SQL转Java Entity</title>

</head>
<body>
<form action="/code/generate" method="post">
    <div>
        <label for="basePackage">包:</label>
        <input type="text" name="basePackage" id="basePackage" value="rog.dark.entity">
    </div>
    <div>
        <label for="ignoreTablePrefix">表前缀:</label>
        <input type="text" name="ignoreTablePrefix" id="ignoreTablePrefix" value="tb">
    </div>
    <div>
        <label for="sql">SQL</label>
        <textarea name="sql" cols="110" rows="30"></textarea>
    </div>
    <div>
        <input type="submit" value="提交">
    </div>
</form>
</body>
</html>