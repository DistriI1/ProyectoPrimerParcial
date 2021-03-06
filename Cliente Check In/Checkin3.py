#!/usr/bin/env python
#enconding: utf-8
'''
Check-In
'''
import socket
import sys
import md5
import time
import datetime
import Turista
def get_specific_input(valid_length, datatype):
    resp = None
    prompt = ' -> '# muestra > mientras espera input
    if datatype == 'int':
        resp = get_input(prompt, lambda x: x.isdigit(), "Maximo " + str(valid_length) + " caracteres numericos", valid_length)
    elif datatype == 'dec':#int and dec
        resp = get_input(prompt, lambda x: not x.isalnum(), "Maximo "+str(valid_length)+" caracteres numericos dec", valid_length)
    elif datatype == 'var':
        resp = get_input(prompt, lambda x: len(x) < valid_length+1, "Maximo "+str(valid_length)+" caracteres", valid_length)
    return resp
def get_input(prompt, test, error="Invalid Input", length=200):
    resp = None
    while True:
        resp = raw_input(prompt)
        print str(resp.isalnum())
        if test(resp) and len(str(resp)) <= length:
            break
        print error
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
    cod_reserva = get_specific_input(10, 'var')
    try:
        envio_mensaje('LISTTURRES', cod_reserva, skt)
        #Recibe respuesta
        mensaje_recibido = ''
        mensaje_recibido = skt.recv(180) #Cuantos bytes?
        cabecera = mensaje_recibido[:66]
        cuerpo = mensaje_recibido[66:]
        mda = md5.new()
        mda.update(cuerpo)
        hashcode = mda.hexdigest()
        print "Head>" + cabecera
        print "Body>" + cuerpo
        if hashcode == cabecera[34:]:#Esta integro?
            resultado = cuerpo[0:3]
            print "res: "+ resultado
            if resultado == 'OKK':
                tipo_tour = cuerpo[3:7]
                print "tip: "+ tipo_tour
                cuerpo_partes = cuerpo[7:].split('|')
                lista_turistas(cod_reserva, tipo_tour, cuerpo_partes, skt)
            elif cuerpo_partes[0] == 'BAD':
                print 'ERROR. Algo salio mal. Intente nuevamente.'
        else:
            print 'ERROR. Falla de integridad en la cadena.\n'
    finally:
        print '================================================================================'
def lista_turistas(codigo_reserva, tipo_tour, cuerpo_partes, skt):
    print ''
    print '======================LISTADO DE TURISTAS=================================='
    print ' > Reserva: ' + codigo_reserva
    if tipo_tour == 'TIT1':
        print ' > Tipo Tour: Aventura Magica'
    elif tipo_tour == 'TIT3':
        print ' > Tipo Tour: Aventura Extrema'
    elif tipo_tour == 'TIT2':
        print ' > Tipo Tour: Aventura Ideal'
    turistas = []
    peso_total = 0
    peso_max_mi = 25
    peso_max_ex = 32
    print "cuepo_partes" +str(len(cuerpo_partes)-1)
    for i in range(0, len(cuerpo_partes)-1):
        turis = Turista.Turista()
        turis.identificacion = cuerpo_partes[i]
        turistas.append(turis)
        print " > Turista " + str(i+1) + ": " + turistas[i].get_identificacion()
        print 'Ingrese el peso de la :'
        peso = get_specific_input(5, 'dec') #validar
        turistas[i].set_peso_maleta(peso)
        peso_total = float(peso_total) + float(peso)
        print "peso "+ str(i) +':'+ turistas[i].get_peso_maleta()
    print "numturis:"+ str(len(turistas))
    print "pesoofi:" +str(peso_max_mi * len(turistas) + 10)
    print 'tipotour' + tipo_tour
    print 'pesototal: ' +str(peso_total)
    if (tipo_tour == 'AVMAGIC') and peso_total > peso_max_mi * len(turistas) + 10:
        print 'Peso total por camarote excedido. Maximo por turista 25kg.'
        print 'Peso maximo permitido: ' + str(peso_max_mi * len(turistas) + 10) + '. Peso actual: ' + str(peso_total)
    elif (tipo_tour == 'AVIDEAL') and peso_total > peso_max_mi * len(turistas) + 10:
        print 'Peso total por camarote excedido. Maximo por turista 25kg.'
        print 'Peso maximo permitido: ' + str(peso_max_mi * len(turistas) + 10) + '. Peso actual: ' + str(peso_total)
    elif tipo_tour == 'AVEXTRE' and peso_total > peso_max_ex * len(turistas) + 10:
        print 'Peso total por camarote excedido. Maximo por turista 32kg'
    else:
        print 'Peso Correcto.' + str(peso_total)
        print 'Enviar cambios? (y/n):'
        resp = get_specific_input(1, 'var')
        if resp == 'y' or resp == 'Y':
            for j in range(0, len(turistas)):
                envio_mensaje('REGPESOMAL', turistas[j].get_identificacion() + '&' + str(turistas[j].get_peso_maleta()), skt)
                mensaje_recibido = ''
                mensaje_recibido = skt.recv(69) #Cuantos bytes?
                cabecera = mensaje_recibido[:66]
                cuerpo=''
                cuerpo = mensaje_recibido[66:]
                mda = md5.new()
                mda.update(cuerpo)
                hashcode = mda.hexdigest()
                print "Head>" + cabecera
                print "Body>" + cuerpo
                if hashcode == cabecera[34:]:
                    if cuerpo == 'OKK':
                        print 'Cambios guardados. Turista ' + str(j+1)
                    elif cuerpo == 'BAD':
                        print 'ERROR. Algo salio mal. Intente nuevamente.'
                else:
                    print 'ERROR. Falla de integridad en la cadena.'
        elif resp == 'n' or resp == 'N':
            print '-------------'

def envio_mensaje(ide, cuerpo, skt):
    tipo_mensaje = 'RQ'
    originador = 'CHIN'
    fecha_mensaje = time.strftime("%Y%m%d%H%M%S")
    cuerpo_length = len(cuerpo)
    mda = md5.new()
    mda.update(cuerpo)
    hash_cuerpo = mda.hexdigest()
    cabecera = tipo_mensaje + originador + fecha_mensaje + ide + ('000' if cuerpo_length < 10 else ('00' if cuerpo_length < 100 else ('0' if cuerpo_length < 1000 else ''))) + str(cuerpo_length) + hash_cuerpo
    mensaje = cabecera + cuerpo
    print mensaje
    skt.sendall(mensaje+'\n')
def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    ip = '192.168.1.5'
    puerto = 2001
    server_address = (ip, puerto)
    print >> sys.stderr, 'Conectando a %s por el puerto %s' % server_address
    sock.connect(server_address)
    opc = -1
    while int(opc) != 2:
        print '=========================== MENU PRINCIPAL -CHECKIN- ============================'
        print '  1. Registro de maletas'
        print '  2. Salir'
        print 'Seleccione una opcion:'
        opc = get_specific_input(1, 'int')
        if int(opc) == 1:
            registro_maleta(sock)
    print '================================================================================'
    print >> sys.stderr, 'Cerrando socket'
    sock.close()
main()
