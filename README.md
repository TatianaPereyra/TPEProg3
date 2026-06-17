# TPE - Programacion 3

## Descripcion
Programa que brinda servicios relacionados a la busqueda de paquetes y la asignacion 
de estos a camiones. Se leen archivos .csv con la informacion de los paquetes y camiones 
y se implementan los servicios de la forma mas optima posible, priorizando el tiempo de busqueda.

Se utilizó la notación BigO para determinar el tiempo de ejecución.

## Servicios de filtrado de Paquetes (Parte 1)
- Servicio 1: Obtener un paquete por su codigo. Se implemento un HashMap que almacena 
  el codigo del paquete como clave y el objeto Paquete como valor.
- Servicio 2: Obtener una lista de paquetes que contengan o no alimentos. Se implementaron 
  dos ArrayList, una para paquetes con alimentos y otra para paquetes sin alimentos, 
  permitiendo retornar la lista correspondiente.
- Servicio 3: Obtener una lista de paquetes en un rango determinado de urgencia. Se implemento 
  un ABB donde cada nodo representa un nivel de urgencia y almacena la lista de paquetes 
  asociados a ese nivel.

## Servicios de asignacion de Paquetes a Camiones (Parte 2)
El objetivo era minimizar el peso total de los paquetes no asignados, respetando las 
restricciones de capacidad y refrigeracion de cada camion. Se utilizaron dos estrategias:

- Backtracking
- Greedy

En ambas estrategias se utilizo una clase Solucion para almacenar y gestionar las asignaciones.
