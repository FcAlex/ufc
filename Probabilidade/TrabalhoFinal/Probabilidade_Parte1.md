
# Trabalho Final Probabilidade
## Parte 01


```python
import numpy as np
import scipy.stats as st
```

---
**1. Seja Z uma distribuição Normal Padrão N(0, 1). Calcule:**


```python
# A) P(0 < Z ≤ 1,3)
z1 = 0
z2 = 1.3
# Normal Padrão
prob = st.norm.cdf(z2,loc=0, scale=1) - st.norm.cdf(z1,loc=0, scale=1)
print("Probabilidade:", prob)
```

    Probabilidade: 0.4031995154143897
    


```python
# B P(−0,8 ≤ Z ≤ 0)
z1 = -0.8
z2 = 0
# Normal Padrão
prob = st.norm.cdf(z2,loc=0, scale=1) - st.norm.cdf(z1,loc=0, scale=1)
print("Probabilidade: ", prob)
```

    Probabilidade:  0.2881446014166033
    


```python
# C  P(1,2 ≤ Z < 1,75)
z1 = 1.2
z2 = 1.75
# Normal Padrão
prob = st.norm.cdf(z2, loc=0, scale=1) - st.norm.cdf(z1,loc=0, scale=1)
print("Probabilidade: ", prob)
```

    Probabilidade:  0.0750105133578911
    


```python
# D P(Z > −0,85)
prob = st.norm.cdf(0.85, loc=0, scale=1)
print("Probabilidade: ", prob)
```

    Probabilidade:  0.8023374568773076
    


```python
# E P(Z ≤ 1,45)
prob = st.norm.cdf(1.45, loc=0, scale=1)
print("Probabilidade: ", prob)
```

    Probabilidade:  0.9264707403903516
    


```python
# P(−1,44 < Z < 0)
z1 = -1.44
z2 = 0
# Normal Padrão
prob = st.norm.cdf(z2, loc=0, scale=1) - st.norm.cdf(z1,loc=0, scale=1)
print("Probabilidade: ", prob)
```

    Probabilidade:  0.42506630046567295
    

---
**2) O diâmetro do eixo principal de um disco rígido segue a distribuição Normal com média 25,08 pol. e desvio
padrão 0,05 pol. Se as especificações para esse eixo são 25,00 ± 0,15 pol., determine o percentual de unidades
produzidas em conformidades com as especificações.**


```python
media = 25.08
desvp = 0.05
z1 = (25.15 - media)/desvp
z2 = (24.85 - media)/desvp
prob = st.norm.cdf(z1, loc=0, scale=1) - 0 # z2 = -4,6....
print("Probabilidade: ", prob)
```

    Probabilidade:  0.9192433407662297
    

---
**3) Uma companhia aérea faz 200 reservas em um voo com capacidade para 188 passageiros. A probabilidade de
um passageiro se apresentar para o voo é de 0.9 e a chegada dos passageiros é assumida ser independente.**

a) Aproxime a probabilidade de todos os passageiros que chegarem consigam seu assento


```python
media = 200 * 0.9
desvp = np.sqrt(media*(1 - 0.9))
z1 = (188 - media + 0.5)/desvp
prob = st.norm.cdf(z1, loc=0, scale=1)
print("Probabilidade: ", prob)
```

    Probabilidade:  0.977436525555662
    

b) Qual a probabilidade aproximada do voo não apresentar assentos vazios?


```python
z1 = (188 - media - 0.5)/desvp
prob = st.norm.cdf(-z1, loc=0, scale=1)
print("Probabilidade: ", prob)
```

    Probabilidade:  0.03854993587177085
    

c) Aproxime o número de reservas que a companhia aérea deve permitir para que a probabilidade de todos os
passageiros que se apresentarem ao voo consigam seus assentos ser equivalente a 95%. (Dica: faça sucessivas
tentativas de valores para o número de reservas.)


```python
media = 193 * 0.95
desvp = np.sqrt(media*(0.05))
z1 = (188 - media + 0.5)/desvp
prob = st.norm.cdf(z1, loc=0, scale=1)
print("Probabilidade: ", prob, "\nNumero de Reservas", 193)
```

    Probabilidade:  0.9555202158026277 
    Numero de Reservas 193
    

---
**4) A distribuição dos pesos de coelhos criados numa granja pode muito bem ser representada por uma distribuição Normal, com média 5 kg e desvio padrão 0,9 kg. Um abatedouro comprará 5000 coelhos e pretende
classificá-los de acordo com o peso do seguinte modo: 15% dos mais leves como pequenos, os 50% seguintes
como médios, os 20% seguintes como grandes e os 15% mais pesados como extras. Quais os limites de peso
para cada classificação?**


```python
media = 5
desvp = 0.9

z3 = 1.04*0.9+5
z1 = -1.04*0.9+5
z2 = 0.39*0.9+5
print(z1, "kg")
print(z2, "kg")
print(z3, "kg")
```

    4.064 kg
    5.351 kg
    5.936 kg
    

---
**5) O diâmetro de um ponto produzido por uma impressora obedece a uma distribuição normal cujo diâmetro
médio é µ = 0.1mm.**

a) Suponha que a especificação exija que o diâmetro do ponto esteja entre 0.06mm e 0.14mm. Se a probabilidade dos pontos atenderem à especificação é desejada ser 0.9973, qual o valor de desvio padrão σ deve ser
obtido?


```python
z1 = 0.14
prob = 0.9973
media = 0.1

print("Desvio Padrão:", (z1 - media)/st.norm.ppf(prob))
```

    Desvio Padrão: 0.014377367674558142
    

b) Assumindo que o desvio padrão do tamanho do ponto é de σ = 0.01mm, se a probabilidade do ponto
atender às especificações for de 0.9973, quais são as especificações? Assuma que os valores de especificação
são simétricos em relação à média.


```python
desvp= 0.01
 
print(media + st.norm.ppf(prob)*desvp)
```

    0.12782150453784602
    

---
**6) Suponha que o tempo necessário para que estudantes completem uma prova tenha distribuição normal com
média 90 minutos e desvio padrão 15 minutos.**

a) Qual é a probabilidade do estudante terminar a prova em menos de 80 minutos?


```python
# < 80
media = 90
desvp = 15
z1 = (80 - 90)/15
prob = st.norm.cdf(z1, loc=0, scale=1)
print("Probabilidade: ", prob)
```

    Probabilidade:  0.2524925375469229
    

b) Em mais de 120 minutos?


```python
# > 120
z1 = (120 - media)/desvp
prob = st.norm.cdf(-z1, loc=0, scale=1)
print("Probabilidade: ", prob)
```

    Probabilidade:  0.022750131948179195
    

c) Entre 75 e 85 minutos?


```python
# > 75 e < 85
z1 = (75 - media)/desvp
z2 = (85 - media)/desvp
prob = st.norm.cdf(z2, loc=0, scale=1) - st.norm.cdf(z1,loc=0, scale=1)
print("Probabilidade: ", prob)
```

    Probabilidade:  0.2107860862503066
    

d) Qual é o tempo necessário para que 98% dos estudantes terminem a prova?


```python
# P(x<=t) = 0.98
# P(z<=z1) = 0.98 Na tabela temos 2.05 mais próximo
t = 2.05*15+90
print(t, "minutos")
```

    120.75 minutos
    

e) Determinar o intervalo simétrico em torno do valor médio que contenha 70% dos valores do tempo
para completarem a prova?


```python
x = st.norm.ppf((1 - 0.7)/2)
 
z1 = x * desvp + media
z2 = -x * desvp + media
print(z1, "e", z2)
```

    0.0896356661050621 e 0.11036433389493791
    

f) Qual é a probabilidade de que, entre 5 estudantes escolhidos ao acaso, 3 deles completem a prova em
menos de 80 minutos?


```python
est = 5
z1 = (80 - 90)/15
prob = st.binom.cdf(3, est, st.norm.cdf(z1, loc=0, scale=1))
print("Probabilidade: ", prob)
```

    Probabilidade:  0.9837830068698056
    

---
**7) Uma maquina produz 400 parafusos, dos quais 5% apresenta agum defeito. Determine a probabilidade de:**

a) No máximo 30 sejam defeituosos;


```python
media = 400 * 0.05
desvp = np.sqrt(400*0.05*(0.95))

z1 = (30 + 0.5 - media)/desvp
prob = st.norm.cdf(z1, loc=0, scale=1)
print("Probabilidade: ", prob)
```

    Probabilidade:  0.991998896498282
    

b) Entre 25 e 45 defeituosos;


```python
z1 = (25 - 0.5 - media)/desvp
z2 = (45 + 0.5 - media)/desvp

prob = st.norm.cdf(z2, loc=0, scale=1) - st.norm.cdf(z1, loc=0, scale=1)
print("Probabilidade: ", prob)
```

    Probabilidade:  0.150949219757894
    

c) mais de 50 parafusos defeituosos;


```python
z1 = (50 + 0.5 - media)/desvp

prob = st.norm.cdf(z1, loc=0, scale=1)
print("Probabilidade: ", prob)
```

    Probabilidade:  0.9999999999986942
    

---
**8) Um fabricante produz anéis de pistão para um motor de automóvel. Sabe-se que o diâmetro do anel possui
distribuição normal com desvio padrão σ = 0,001 mm. Uma amostra aleatória de 15 anéis produziu um
diâmetro médio de x = 74,036 mm. Construa intervalos de confiança unilateral e bilateral de 95%;**


```python
# IC bilateral de 95%
zEsc = st.norm.isf(0.975, loc=0, scale=1)
print(zEsc) #-1.95996398454

zEsc1 = st.norm.ppf(.95)
print(zEsc1) #1.64485362695

X = [91.6, 88.75, 90.8, 89.95, 91.3]
medX = np.mean(X)
n = len(X)
zA2 = zEsc * (-1)
sigma = 3 #Variancia Conhecida
Linf = medX - zA2 *sigma/np.sqrt(n)
Lsup = medX + zA2 *sigma/np.sqrt(n)

print(Linf)
print(Lsup)
```

    -1.959963984540054
    1.6448536269514722
    87.85043237827024
    93.10956762172974
    

---
**9) O rendimento de um processo químico está sendo estudado. De experiências prévias com esse processo,
sabe-se que o rendimento é normalmente distribuído e σ = 3. Os últimos cinco dias de operação da planta
resultaram nos seguintes rendimento percentuais: 91,6; 88,75; 90,8; 89,95 e 91,3. Encontre um intervalo com
95% de confiança para o rendimento médio verdadeiro.**


```python
## Normal
# IC bilateral de 95%
zEsc1 = st.norm.isf(0.975, loc=0, scale=1)
print(zEsc1) 


medX = 3250
n = 12
zA2 = zEsc1 * (-1)
sigma = 31.6 #Variancia Conhecida
Linf = medX - zA2 *sigma/np.sqrt(n)
Lsup = medX + zA2 *sigma/np.sqrt(n)

print(Linf)
print(Lsup)

# IC bilateral de 99%
zEsc2 = st.norm.isf(0.995, loc=0, scale=1)
print(zEsc2) 


zA3 = zEsc2 * (-1)
Linf1 = medX - zA3 *sigma/np.sqrt(n)
Lsup1 = medX + zA3 *sigma/np.sqrt(n)

print(Linf1)
print(Lsup1)
```

    -1.959963984540054
    3232.1209454015966
    3267.8790545984034
    -2.5758293035489004
    3226.5029392797105
    3273.4970607202895
    

---
**10) Um engenheiro civil está analisando a resistência à compressão do concreto. A resistência à compressão tem
distribuição normal com σ = 31, 6. Uma amostra aleatória de 12 corpos-de-prova tem uma resistência média
à compressão x = 3250. Construa intervalos de confiança bilaterais de 95% e 99% para a média.**


```python
## Normal
# IC bilateral de 95%
zEsc1 = st.norm.isf(0.975, loc=0, scale=1)
print(zEsc1) 


medX = 3250
n = 12
zA2 = zEsc1 * (-1)
sigma = 31.6 #Variancia Conhecida
Linf = medX - zA2 *sigma/np.sqrt(n)
Lsup = medX + zA2 *sigma/np.sqrt(n)

print(Linf)
print(Lsup)

# IC bilateral de 99%
zEsc2 = st.norm.isf(0.995, loc=0, scale=1)
print(zEsc2) 


zA3 = zEsc2 * (-1)
Linf1 = medX - zA3 *sigma/np.sqrt(n)
Lsup1 = medX + zA3 *sigma/np.sqrt(n)

print(Linf1)
print(Lsup1)
```

    -1.959963984540054
    3232.1209454015966
    3267.8790545984034
    -2.5758293035489004
    3226.5029392797105
    3273.4970607202895
    
