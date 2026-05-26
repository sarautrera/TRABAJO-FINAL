# Resumen del fichero: compila el proyecto con JavaFX y lanza la aplicacion principal.

$ErrorActionPreference = "Stop"

$javafxLib = Join-Path $PSScriptRoot "lib\javafx-sdk-25.0.3\lib"
$outDir = Join-Path $PSScriptRoot "out_run"

if (-not (Test-Path $javafxLib)) {
    throw "No se encuentra JavaFX en $javafxLib"
}

if (Test-Path $outDir) {
    Remove-Item -Recurse -Force $outDir
}
New-Item -ItemType Directory -Path $outDir | Out-Null

$sources = Get-ChildItem -Path (Join-Path $PSScriptRoot "src\main\java") -Recurse -Filter *.java |
    ForEach-Object { $_.FullName }

javac -encoding UTF-8 `
    --module-path $javafxLib `
    --add-modules javafx.controls,javafx.fxml `
    -d $outDir `
    $sources

java `
    --module-path $javafxLib `
    --add-modules javafx.controls,javafx.fxml `
    -cp $outDir `
    es.proyecto.juego.ui.MainApp
