@echo off

if "%~1"=="" (
    echo Vous devez passer l'argument reset ou install au script
    goto :eof
)

if "%~1"=="reset" (
    if not exist ".\fr.erwan.babyphone.client\src\configuration\Constantes_gen.java" (
        echo Vous ne pouvez pas réinitialiser si vous n'avez pas installé
        exit /b 1
    )
    move ".\fr.erwan.babyphone.client\src\configuration\Constantes.java"  ".\fr.erwan.babyphone.client\src\configuration\Constantes.java.bak"
    move ".\fr.erwan.babyphone.client\src\configuration\Constantes_gen.java" ".\fr.erwan.babyphone.client\src\configuration\Constantes.java"
)

if "%~1"=="install" (
    if exist ".\fr.erwan.babyphone.client\src\configuration\Constantes_gen.java" (
        echo Vous ne pouvez pas installer si vous n'avez pas réinitialisé
        exit /b 1
    )
    move ".\fr.erwan.babyphone.client\src\configuration\Constantes.java" ".\fr.erwan.babyphone.client\src\configuration\Constantes_gen.java"
    move ".\fr.erwan.babyphone.client\src\configuration\Constantes.java.bak" ".\fr.erwan.babyphone.client\src\configuration\Constantes.java"
)

echo Vérification :
type ".\fr.erwan.babyphone.client\src\configuration\Constantes.java" | findstr "String HOST"
type ".\fr.erwan.babyphone.client\src\configuration\Constantes.java" | findstr "String SSH_USERNAME"

