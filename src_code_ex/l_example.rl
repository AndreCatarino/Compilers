import CiberAV

/*
function beaconAngle(integer bn):
	return bn
end
*/

/*
function groundType():
    integer a = 0
	return a
end
*/

/*
function gotoBeacon(integer bn):

    while (beaconAngle(bn) < -10 or beaconAngle(bn) > 10 ):
        move -60 60
        // apply not needed
    end

    while (groundType() different from bn):
        one of: // or "any of" ?
            case (beaconAngle(bn) < -5 ):
                move 80 70
            case (beaconAngle(bn) > 5 ):
                move 70 80
            else:
                move 80 80
        endchoose
    endwhile
    
    stop

endfunction
*/

function gotoBeaconAvoidingObstacles(integer bn):

    while (beaconAngle(bn) < -10 or beaconAngle(bn) > 10 ):
        move -60 60
    end

    while (groundType() different from bn):
        integer fd = obstacleDistance(FRONTSENSOR)
        integer ld = obstacleDistance(LEFTSENSOR)
        integer rd = obstacleDistance(RIGHTSENSOR)
        integer ba = beaconAngle(bn)

        print 'distances: '
	print fd
	print ', '
	print ld
	print ', '
	print rd
	print '; '
	print 'angle: '
	print ba
	print '\n'

        one of: // or "any of" ?
            case (fd < 80):
                if (ld < 80):
                    move 50 -50
                else:
                    move -50 50
                endif
            case (ld < 80):
                move 80 40
            case (rd < 80):
                move 40 80
            case (beaconAngle(bn) < -5):
                move 80 40
            case (beaconAngle(bn) > 5):
                move 40 80
            else:
                move 80 80
        endchoose
    endwhile

    move 0 0
endfunction

function gotoStartAvoidingObstacles():
    while (startAngle() < -10 or startAngle() > 10):
        move -80 80
    end

    while (startDistance() > 50):
        integer fd = obstacleDistance(FRONTSENSOR)
        integer ld = obstacleDistance(LEFTSENSOR)
        integer rd = obstacleDistance(RIGHTSENSOR)
        // integer ba = beaconAngle(bn)


	print 'distances: '
	print fd
	print ', '
	print ld
	print ', '
	print rd
	print '\n'

        one of: // or "any of" ?
            case (fd < 80):
                if (ld < 80):
                    move 50 -50
                else:
                    move -50 50
                endif
            case (ld < 80):
                move 80 40
            case (rd < 80):
                move 40 80
            case (startAngle() < -5):
                move 80 44
            case (startAngle() > 5):
                move 40 80
            else:
                move 80 80
        endchoose
    endwhile

    move 0 0
endfunction

function gotoStart():
    while (startAngle() < -10 or startAngle() > 10):
        move -80 80
        // auto apply
    end

    while (startDistance() > 50):
        one of: // or "any of" ?
            case (startAngle() < -5):
                move 80 70
            case (startAngle() > 5):
                move 70 80
            else:
                move 80 80
            // auto apply
        endchoose
    endwhile

    move 0 0
    // auto apply
endfunction

function main():
    init('Grimmy bear', 0) // init as keywork like move ?

    print 'Going to beacon 1\n'
    gotoBeaconAvoidingObstacles(1)

    print 'Signaling arrival to target 1\n'
    pickup() // pickup as keywork like move ?
	
    print 'Signaling returning to start spot\n'
    returning() // returning as keywork like move ?
	
    print 'Going to start spot\n'
    gotoStartAvoidingObstacles()
    	    	
    print 'Signaling end of trial\n'
    finish()
	
    // for example

    for (integer i = 0, i < 10 ): // for i in range(0, 10)
        break
    end

    for (integer i = 10, i >= 0, -1 ): // for i in range(10, 0, -1)
        break
    end

    integer arr = [1, 2]

    for e in arr:
        print e
    endfor

    print 'Bye!\n'
    return 0
endfunction

