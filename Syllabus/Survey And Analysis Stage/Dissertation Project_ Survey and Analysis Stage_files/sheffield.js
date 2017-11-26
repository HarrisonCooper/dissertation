/*****************************************************
DEPRECATED - DO NOT USE OR LINK TO THIS FILE (as from July 2014)
*****************************************************/
$(document).ready(function(){

	// add captions to body text images
	$('#content img[title]')
		.one( 'load', function() {
			$(this)
				.wrap( $('<div />').addClass( 'caption ' + this.className).width(this.width) )
				.removeAttr('class')
				.after( $('<span />').text($(this).attr('title')) );
		})
		// fix for browsers that don't fire load event for cached images
		.each( function() {
			if ( this.complete ) { $(this).trigger('load'); }
		});


	// rotating banners
	$('div.homepageBanner').each( function() {
		$(this).children('img:first-child').show();	// for IE6
		var images = $(this).children('img');
		if ( images.length < 2 ) return;
		var current = 0;
		setInterval( function() {
			var previous = current;
			current = ++current % images.size();;
			images.eq( current ).show( 0, function() {
				images.eq( previous ).hide();
			} );
		}, 5000 );
	} );
	
	// <input> placeholder fallback
	if ( ! ('placeholder' in document.createElement('input')) ) {
		$('input[placeholder]').each( function() {
				this.value = $(this).attr('placeholder');
		}).focus( function() {
			if ( this.value == $(this).attr('placeholder') ) {
				this.value = '';
			}
		});
	}	
});

$(window).bind( 'load resize', function() {
	// equalise styled box heights
	$('table.layout tr div').filter('.feature, .highlight').closest('tr').each( function() {
		var boxes = $(this).find('div').filter('.feature, .highlight')
			.filter( function() {
				if ( $(this).closest('td').find('div.feature, div.highlight').size() > 1 ) { return false; }
				return (! $(this).parents('td[rowspan]').length > 0 );
			});
		if ( boxes.size() <= 1 ) return;
		var maxHeight = 0;
		boxes.each( function() {
			$(this).height('auto');
			if ( $(this).height() > maxHeight ) {
				maxHeight = $(this).height();
			}
		});
		boxes.each( function() {
			$(this).height( maxHeight );
		} );
	});
});