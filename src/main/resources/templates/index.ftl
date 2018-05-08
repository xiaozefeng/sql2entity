<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SQL转Java Entity</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form action="/codes/generate" method="post">
        <div class="form-group">
            <label for="basePackage">Package</label>
            <input type="text" class="form-control" name="basePackage" id="basePackage"
                   placeholder="example: org.dark.entity" value="org.dark.entity" required>
        </div>
        <div class="form-group">
            <label for="ignoreTablePrefix">需要忽略的前缀</label>
            <input type="text" class="form-control" name="ignoreTablePrefix" id="ignoreTablePrefix"
                   placeholder="需要忽略的前缀" value="TB">
        </div>
        <div class="form-group">
            <label for="sql">SQL</label>
            <textarea name="sql" rows="20" cols="110" class="form-control"></textarea>
        </div>

        <div class="checkbox">
            <label>
                <input type="checkbox" checked="checked" readonly> jpa代码风格
            </label>
        </div>
        <button type="submit" class="btn btn-default">提交</button>
    </form>
</div>
</body>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>