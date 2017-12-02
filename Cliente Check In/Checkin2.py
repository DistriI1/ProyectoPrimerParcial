#!/usr/bin/env python
import socket
import sys
import md5
import time
import datetime
import Turista
def get_specific_input(valid_length, datatype):
    resp=None
    prompt=' -> '# muestra > mientras espera input
    if (datatype == 'int'):
        resp = get_input(prompt,lambda x:x.isdigit(),"Maximo "+str(valid_length)+" caracteres numericos",valid_length)   #lambda funcion en una sola linea, : para retornar
        #resp = resp.rjust(valid_length,' ')    #rjust para completar la longitud de un string con cualquier caracter
    elif (datatype == 'dec'): #int and dec
        resp = get_input(prompt, lambda x:not x.isalnum(), "Maximo "+str(valid_length)+" caracteres numericos dec",valid_length)
    elif (datatype == 'var'):
        resp= get_input(prompt,lambda x:len(x)<valid_length+1,"Maximo "+str(valid_length)+" caracteres",valid_length)   
        resp=resp.rjust(valid_length,' ')
    return resp
def get_input(prompt, test, error="Invalid Input", length=200):
    resp = None
    while True:
        resp = raw_input(prompt)
        print str(resp.isalnum())
        if test(resp) and len(str(resp))<=length:
            break
        print (error)
    return resp
def validate_date(date_text):
    try:
        return datetime.datetime.strptime(date_text.strip(), '%Y-%m-%d')
    except ValueError:
        return datetime.datetime.strptime(time.strftime('%Y-%m-%d'), '%Y-%m-%d')
def validar_peso(tipo_tour,peso):
    if((tipo_tour == 'AVMAGIC' or tipo_tour == 'AVIDEAL') and peso <= 25.0):
        return True
    elif (tipo_tour == 'AVEXTRE') and peso <= 32.0:
        return True
    else:
        print 'Peso excedido.'
        return False
def registro_maleta(skt):
    print ' '
    print '======================REGISTRO DE MALETAS=================================='
    print 'Ingrese el codigo de reserva:'
    cod_reserva = get_specific_input(10, 'var')
    try:
        envio_mensaje('LISTTURRES', cod_reserva,skt)
        #Recibe respuesta
        mensaje_recibido = skt.recv(180) #Cuantos bytes?
        cabecera = mensaje_recibido[:66]
        cuerpo = mensaje_recibido[66:]
        mda = md5.new()
        mda.update(cuerpo)
        hashcode = mda.hexdigest()
        print "Head>" + cabecera
        print "Body>" + cuerpo
        if hashcode == cabecera[34:]:
            cuerpo_partes = cuerpo.split('|')
            if cuerpo_partes[0] == 'OKK':
                tipoTour = cuerpo_partes[1]
                #identificacionTurista = identificaciones.split('|') #arrar identificaciones
                print ' '
                print '======================LISTADO DE TURISTAS=================================='
                print ' > Reserva: ' + cod_reserva
                if tipoTour == 'AVMAGIC':
                    tipoTour = 'Aventura Magica'
                elif tipoTour == 'AVEXTRE':
                    tipoTour = 'Aventura Extrema'
                elif tipoTour == 'AVIDEAL':
                    tipoTour = 'Aventura Ideal'
                print ' > Tipo Tour: '+ tipoTour
                for i in range(2, len(cuerpo_partes) - 1):
                    turista = cuerpo_partes[i]
                    print " > Turista " + str(i-1) + ": " + turista
                    print 'Ingrese el peso de la maleta:'
                    peso = get_specific_input(5, 'dec') #validar

                    print 'Enviar cambios? (y/n):'
                    resp = get_specific_input(1, 'var')
                    if resp == 'y' or resp == 'Y':
                        envio_mensaje('REGPESOMAL', turista + '|' + peso, skt)
                        mensaje_recibido = skt.recv(69) #Cuantos bytes?
                        cabecera = mensaje_recibido[:66]
                        cuerpo = mensaje_recibido[66:]
                        mda = md5.new()
                        mda.update(cuerpo)
                        hashcode = mda.hexdigest()
                        if hashcode == cabecera[34:]:
                            if cuerpo == 'OKK':
                                print 'Cambios guardados.'
                            elif cuerpo == 'BAD':
                                print 'ERROR. Algo salio mal. Intente nuevamente.'
                        else:
                            print 'ERROR. Falla de integridad en la cadena.\n'
                    elif resp == 'n' or resp == 'N':
                        break
            elif cuerpo_partes[0] == 'BAD':
                print 'ERROR. Algo salio mal. Intente nuevamente.'     
        else:
            print 'ERROR. Falla de integridad en la cadena.\n'
    finally:
        print '================================================================================'
def envio_mensaje(id, cuerpo, skt):
    tipo_mensaje = 'RQ'
    originador = 'CHIN'
    fecha_mensaje = time.strftime("%Y%m%d%H%M%S")
    cuerpo_length = len(cuerpo)
    mda = md5.new()
    mda.update(cuerpo)
    hash_cuerpo = mda.hexdigest()
    cabecera = tipo_mensaje + originador + fecha_mensaje + id + ('000' if cuerpo_length < 10 else ('00' if cuerpo_length < 100 else ('0' if cuerpo_length < 1000 else ''))) + str(cuerpo_length) + hash_cuerpo
    mensaje =  cabecera + cuerpo
    skt.send(mensaje)
def main():
    sock=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    ip='localhost'
    puerto=2000
    server_address = (ip, puerto)
    print >> sys.stderr, 'Conectando a %s por el puerto %s' % server_address
    sock.connect(server_address)
    opc=-1
    while int(opc)!=2:
        print '\n=========================== MENU PRINCIPAL -CHECKIN- ============================'
        print '     1. Registro de maletas'
        print '     2. Salir'
        print 'Seleccione una opcion:'
        opc=get_specific_input(1,'int')
        if int(opc)==1:
            registro_maleta(sock)
            #opcion_registrarusuario(tipo_mensaje,originador,sock)
    print '================================================================================'
    print >>sys.stderr, 'Cerrando socket'
    sock.close()
main()