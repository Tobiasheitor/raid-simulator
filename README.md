# copy.bat

Script que simula RAID 1.

## Installation

Clone o projeto.

```bash
git clone https://github.com/Tobiasheitor/raid-simulator.git
```

## Usage
Acesse a pasta aonde se encontra o copy.bat e execute passando o diretório de origem e de destino como parâmetro.
O diretório de origem é o diretório que você deseja fazer o RAID 1, o de destino é onde os arquivos serão copiados.
```bash
copy.bat {diretorio origem} {diretorio destino}
```
## Exemplo
```bash
copy.bat "C:\Users\usuario\Desktop\origem" "C:\Users\usuario\Desktop\destino"
```

** para parar a execussão use Ctrl + c.