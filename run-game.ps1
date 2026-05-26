# Comentario de estudiante: aqui se ejecuta una orden del script.
$ErrorActionPreference = "Stop"

# Comentario de estudiante: aqui se ejecuta una orden del script.
$javafxLib = Join-Path $PSScriptRoot "lib\javafx-sdk-25.0.3\lib"
# Comentario de estudiante: aqui se ejecuta una orden del script.
$outDir = Join-Path $PSScriptRoot "out_run"

# Comentario de estudiante: aqui se ejecuta una orden del script.
if (-not (Test-Path $javafxLib)) {
    # Comentario de estudiante: aqui se ejecuta una orden del script.
    throw "No se encuentra JavaFX en $javafxLib"
# Comentario de estudiante: aqui se ejecuta una orden del script.
}

# Comentario de estudiante: aqui se ejecuta una orden del script.
if (Test-Path $outDir) {
    # Comentario de estudiante: aqui se ejecuta una orden del script.
    Remove-Item -Recurse -Force $outDir
# Comentario de estudiante: aqui se ejecuta una orden del script.
}
# Comentario de estudiante: aqui se ejecuta una orden del script.
New-Item -ItemType Directory -Path $outDir | Out-Null

# Comentario de estudiante: aqui se ejecuta una orden del script.
$sources = Get-ChildItem -Path (Join-Path $PSScriptRoot "src\main\java") -Recurse -Filter *.java |
    # Comentario de estudiante: aqui se ejecuta una orden del script.
    ForEach-Object { $_.FullName }

# Comentario de estudiante: aqui se ejecuta una orden del script.
javac -encoding UTF-8 `
    --module-path $javafxLib `
    --add-modules javafx.controls,javafx.fxml `
    -d $outDir `
    $sources

# Comentario de estudiante: aqui se ejecuta una orden del script.
java `
    --module-path $javafxLib `
    --add-modules javafx.controls,javafx.fxml `
    -cp $outDir `
    es.proyecto.juego.ui.MainApp
