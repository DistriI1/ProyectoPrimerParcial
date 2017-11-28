#!/usr/bin/env python
import socket
import sys
import md5
import time
import datetime
def get_specific_input(valid_length,is_numeric):
    resp=None
    prompt='-> '# muestra > mientras espera input
    if (is_numeric):
        resp = get_input(prompt,lambda x:x.isdigit(),"Maximo "+str(valid_length)+" caracteres numericos",valid_length)   #lambda funcion en una sola linea, : para retornar
        resp = resp.rjust(valid_length,' ')    #rjust para completar la longitud de un string con cualquier caracter
    else:
        resp= get_input(prompt,lambda x:len(x)<valid_length+1,"Maximo "+str(valid_length)+" caracteres",valid_length)   
        resp=resp.rjust(valid_length,' ')
    return resp
def get_input(prompt,test,error="Invalid Input",length=200):
    resp = None
    while True:
        resp = raw_input(prompt)                    
        if test(resp) and len(str(resp))<=length:
            break
        print (error)
    return resp
def validate_date(date_text):
    try:
        return datetime.datetime.strptime(date_text.strip(), '%Y-%m-%d')
    except ValueError:
        return datetime.datetime.strptime(time.strftime('%Y-%m-%d'), '%Y-%m-%d')
def registro_maleta(skt):
    print ' '
    print '======================REGISTRO DE MALETAS=================================='
    print 'Ingrese el codigo de reserva:'
    cod_reserva = get_specific_input(10,False)
    #Armado del mensaje
    tipo_mensaje = 'RQ'
    originador = 'CHIN'
    fecha_mensaje = time.strftime("%Y%m%d%H%M%S")
    id_mensaje='LISTTURRES'
    cuerpo_mensaje = cod_reserva
    cuerpo_mensaje_length = len(cuerpo_mensaje)
    mda = md5.new()
    mda.update(cuerpo_mensaje)
    hash_cuerpo = mda.hexdigest()
    cabecera = tipo_mensaje+originador+fecha_mensaje+id_mensaje+('000' if cuerpo_mensaje_length<10 else ('00' if cuerpo_mensaje_length<100 else ('0' if cuerpo_mensaje_length<1000 else '')))+str(cuerpo_mensaje_length)+hash_cuerpo
    mensaje = cabecera + cuerpo_mensaje
    #Envio del mensaje
    skt.send(mensaje)
    #Recibe respuesta
def main():
    tipo_mensaje='RQ'
    originador='ENRO'
    sock=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    ip='localhost'
    puerto=2000
    server_address = (ip, puerto)
    print >> sys.stderr, 'Conectando a %s por el puerto %s' % server_address
    sock.connect(server_address)
    opc=-1
    while int(opc)!=2:
        print '\n=========================== MENU PRINCIPAL -CHECKIN- ============================'
        print '     1. Registrar maleta'
        print '     2. Salir'
        print ''
        print 'Seleccione una opcion:'
        opc=get_specific_input(1,True)
        if int(opc)==1:
            print "Opcion 1:"
            registro_maleta(sock)
            #opcion_registrarusuario(tipo_mensaje,originador,sock)
    print '================================================================================'
    print >>sys.stderr, 'Cerrando socket'
    sock.close()
main()