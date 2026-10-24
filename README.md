# LISO AI — projeto Android com compilação automática de APK

O projeto já inclui um fluxo do GitHub Actions para compilar o APK automaticamente na nuvem. Não precisa instalar Android Studio nem Gradle no celular.

## Como obter o APK pelo GitHub

1. Crie um repositório no GitHub chamado `LISO-AI`.
2. Envie todos os arquivos e pastas deste projeto para o repositório, mantendo a pasta `.github/workflows/build-apk.yml`.
3. Abra a aba **Actions** do repositório e selecione **Build LISO AI APK**.
4. Toque em **Run workflow** (ou espere a compilação começar após enviar o código).
5. Quando o processo terminar com um visto verde, abra a execução e baixe o arquivo **LISO-AI-APK** na seção **Artifacts**.
6. Extraia o ZIP baixado; dentro estará `app-debug.apk`. Abra esse APK no Android e permita a instalação por essa fonte se o Android pedir.

O GitHub compila o APK em um computador na nuvem. Este pacote não contém ainda um APK compilado: o APK será criado quando o fluxo do GitHub Actions for executado. A compilação de debug não é uma versão assinada para publicação na Play Store.

## O que inclui
- Interface escura LISO AI.
- Diagnóstico básico do fabricante/modelo/versão Android.
- Atalhos para configurações de Tela, Opções do desenvolvedor e Bateria.
- Sem root, sem coleta de dados e sem permissões especiais.

## Limitações
O Android restringe apps comuns de alterar o comportamento de outros apps. O LISO AI oferece atalhos para configurações que o usuário pode mudar manualmente; não promete dobrar FPS nem aumentar a potência física do aparelho.
