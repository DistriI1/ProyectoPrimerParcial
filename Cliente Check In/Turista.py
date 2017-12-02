class Turista:
    'Clase Turista'

    def __init__(self):
        self.identificacion = ""
        self.reserva = ""
        self.peso = ""
    def get_identificacion(self):
        return self.identificacion
    def set_identificacion(self, id):
        self.identificacion= id
    def get_reserva(self):
        return self.reserva
    def set_reserva(self,res):
        self.reserva = res
    def get_peso_maleta(self):
        return self.peso
    def set_peso_maleta(self,peso):
        self.peso = peso