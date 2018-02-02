var i = 0 ;
var swaplist = new Array() ;
    swaplist.length = 0 ;
var sourcedetect = 0 ;
var holdimage = new Image () ;
var utilitySource = "text" ;

swaplist.length = 0 ;

var xwidth = 430 ;
var yheight = 520 ;

function wrapperOpen (file) {
	utility = eval ( 'window.open ( file, "MultiPurpose","WIDTH=' + xwidth + ',HEIGHT=' + yheight + ',RESIZABLE=YES,SCROLLBARS=YES")' ) ;
	clearTimeout(openit) ;
	utility.focus()
}


function OpenUtility (utilitySource, xpixels, ypixels) {

	var default_width = 420 ;
	var default_height = 520 ;

	if ( !xpixels ) xwidth = default_width
  else xwidth = xpixels ;
  if ( !ypixels ) hoehe = default_height
  else yheight = ypixels ;
  if ( window.utility ) {
		if ( !utility.closed ) utility.close();
		openit = eval ( 'setTimeout("wrapperOpen(\'' + utilitySource + '\')",500)' )
  }
  if ( !window.utility ) openit = eval ( 'setTimeout("wrapperOpen(\'' + utilitySource + '\')",500)' )
}


function NoAction () {
}

function preload ( imagename, imagesource ) {
	i = swaplist.length ;
	eval ( 'swapimage' + i + ' = new Image ()' ) ;
	eval ( 'swapimage' + i + '.src = "' + imagesource + '"' ) ;
	swaplist[i] = [ imagename, eval ( 'swapimage' + i + '.src' ) ]
}

function swap ( imagename ) {
	
	for ( i = 0 ; i < swaplist.length; i++ ) if ( imagename == swaplist[i][0] )	sourcedetect = i ;
	holdimage.src = document.images[imagename].src ;
	document.images[imagename].src = swaplist[sourcedetect][1] ;
	swaplist[sourcedetect][1] = holdimage.src
	
} // end of swap