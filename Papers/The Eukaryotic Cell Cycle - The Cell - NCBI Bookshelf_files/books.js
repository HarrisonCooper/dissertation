jQuery(document).ready(function () {
	bkInlineAudio();
	bkContents();
	bkSearch();
	bkEpubLink();
	bkTglCit();
	bkCitAvailLnk();
	bkTglGlossLnks();
});

//============================================================================
// book inline audio (pronunciation)
//============================================================================
function bkInlineAudio() {
	
	var lnks = jQuery('a.bk_listen');
	
	if (lnks.length == 0) {
		return;
	}
	
	lnks.click(function (e) {
		var plyr = jQuery('#' + jQuery(this).attr('data-player-rid'));
		if (plyr.length == 1) {
			e.preventDefault();
			plyr.ncbimedia('play');
			return false;
		} else {
			return;
		}
	});
}

//============================================================================
// book contents
//============================================================================

function bkContents() {
	
	var triggers = jQuery('.bk_cntns');
	var container = jQuery('#bk_toc_contnr');
	
	//see if we need it at all
	if (triggers.length == 0) {
		return;
	}
	
	// the toc
	var getToc = function (callback) {
		
		if (jQuery('#source-contents').length > 0) {
			triggers.bind("ncbipopperopencomplete", function () {
				bkPopperScroll(container);
			});
			callback();
			return container;
		}
		
		var tocHref = triggers.first().attr('href');
		var tocUrl = tocHref + ((tocHref.match(/\?/)) ? '&': '?') + 'report=toc';
		var selfUrl = jQuery('meta[name=bk-non-canon-loc]').attr('content');
		
		jQuery.ajax({
			url: tocUrl,
			success: function (data) {
				//get toc data
				var _toc = jQuery(data).find('.toc-page-link,#source-contents');
				
				//identify the link to current page
				var currLnk = _toc.find('a[href = "' + selfUrl + '"]');
				currLnk.attr('id', 'bk_current_entry');
				if (jQuery('meta[name=ncbi_pdid]').attr('content') == 'book-part') {
					currLnk.attr('href', '#top');
				}
				
				//set and callback
				container.append(_toc);
				callback(container);
				
				// intiate the toc
				jQuery('body').bkInitToc();
				
				//fire even
				container.trigger("tocloaded");
			}
		});
	}
	//intiate popper
	triggers.each(function (i) {
		var triggerPos = (i == 0) ? 'bottom left': 'bottom right';
		var adjFit = (i == 0) ? 'none': 'autoAdjust';
		jQuery(this).ncbipopper({
			triggerPosition: triggerPos,
			destPosition: 'top left',
			adjustFit: adjFit,
			destText: getToc,
			openEvent: 'click',
			openAnimation: 'fadeIn',
			closeEvent: 'click',
			closeAnimation: 'fadeOut',
			isDestTextCacheable: false
		});
	});
	
	//bind scroll to
	container.on("tocloaded", function () {
		bkPopperScroll(jQuery(this));
	});
}

//============================================================================
function bkPopperScroll(dest) {
	var selectedLink = jQuery('#bk_current_entry');
	if (selectedLink.length > 0) {
		dest.scrollTo(selectedLink, {
			offset: -200, duration: 400
		});
	}
}

//============================================================================
// book search (should become obsolete when book filter in url)
//============================================================================

function bkSearch() {
	jQuery('form#bk_srch').submit(function (f) {
		var term = jQuery('#bk_term').attr('value');
		if (term.replace(/^\s+/, '').length == 0) {
			return false;
		} else {
			f.preventDefault();
			var extr_limit = jQuery(this).attr('data-bk-extr-lmt');
			term = (extr_limit != undefined) ? term + ' AND ' + extr_limit: term;
			var url = jQuery(this).attr('action') + '?term=' + encodeURIComponent(term).replace(/%20/g, '+');
			location.href = url;
		}
	});
}

//============================================================================
// add dialog to epub link
//============================================================================
function bkEpubLink() {
	var $link = jQuery('.bk_epub_link');
	// Return if there is no epub link.
	if (! $link) {
		return;
	}
	
	var dialogDiv =
	'<div title="Making content easier to read in Bookshelf" ' +
	'     id="epubDialog" ' +
	'     style="display: none">' +
	'  <p>We are experimenting with display styles that make it easier to read books and documents ' +
	'    in Bookshelf. ' +
	'    Our first effort uses ebook readers, which have several "ease of reading" ' +
	'    features already built in.</p>' +
	'  <p>The content is best viewed in the <em>iBooks ' +
	'    reader</em>. You may notice problems with the display of some features of books or documents' +
	'    in other eReaders.</p>' +
	'  <button id="cancelEpub">Cancel</button>' +
	'  <button id="downloadEpub" style="float: left">Download</button>        ' +
	'</div>';
	
	// Initialize the dialog
	var $dialog = jQuery(dialogDiv).ncbidialog({
		autoOpen: false,
		modal: true,
		width: "500px"
	});
	
	// Set the link event to open the dialog
	$link.on("click", function (event) {
		//var h = $dialog.ncbidialog('option', 'height');
		//console.info("Initial height per ncbidialog is " + h);
		//console.info("Grabbing css prop directly, height is " + $dialog.css('height'));
		
		event.preventDefault();
		// do not let the link click event fire
		$dialog.ncbidialog("open");
		// open the dialog
		
		// Get rid of vertical scrollbar.  See JSL-1849 and JSL-1850.  Just add 5 px
		// to the height.
		$dialog.height($dialog.height() + 5);
	});
	
	// Set the event handler for the cancel button
	jQuery('#cancelEpub').click(function () {
		$dialog.ncbidialog("close");
	});
	
	// Set the event handler for the download button
	jQuery('#downloadEpub').click(function () {
		$dialog.ncbidialog("close");
		// hide dialog
		window.location.href = $link.prop("href");
		// navigate to next page
	})
}


//============================================================================
// toggle bibliographic details in book-page-banner if there are a lot of them
//============================================================================
function bkTglCit() {
	var cit = jQuery('._bk_pgbnr_cit');
	if (! cit || cit.height() < 126) {
		return;
	}
	
	var ln = jQuery('._bk_pgbnr_cit_ln');
	var h = 0;
	ln.each(function (i) {
		h = h + jQuery(this).height();
		if (i != 0 && h > 90) {
			jQuery(this).addClass('_bk_pgbnr_cit_ln_hdn');
		}
	});
	
	var hd = jQuery('._bk_pgbnr_cit_ln_hdn');
	if (! hd) {
		return;
	}
	hd.hide();
	cit.after('<a href="#" class="small" id="_bk_cit_tgl">Show details</a>');
	jQuery('#_bk_cit_tgl').click(function () {
		if (hd.is(':hidden')) {
			hd.show();
			jQuery(this).text('Hide details');
		} else {
			hd.hide();
			jQuery(this).text('Show details');
		}
		return false;
	});
};

//============================================================================
// add URL to "available at" part in citation
//============================================================================
function bkCitAvailLnk() {
	var bk_l = jQuery(location);
	jQuery('.bk_cite_avail').text(' Available from: ' + bk_l.attr('href').replace(bk_l.attr('hash'), ''));
	//jQuery('.bk_cite_avail').css('word-wrap', 'break-word');
};


//============================================================================
// toggle glossary links
//============================================================================

function bkTglGlossLnks() {
	
	var toggle_element = jQuery("a.toggle-glossary-link");
	if (! toggle_element) {
		return;
	}
	checkGlossLinks('init');
	
	toggle_element.click(function () {
		checkGlossLinks();
		return false;
	});
	
	jQuery('body').on('click', 'a.def', function (e) {
		e.preventDefault();
		if (jQuery(this).hasClass('def_inactive')) {
			return false;
		} else {
			var href = jQuery(this).attr('href');
			return startTarget(href, 'item', 800, 250);
			//startTarget in pmc common js
		}
	});
}

// This function gets called on document ready and whenever the user clicks on
// the "enable/disable glossary links" control.
function toggleglossarylinks_(cookie_value) {
	
	var toggle_element = jQuery(".toggle-glossary-link");
	var glossary_links = jQuery(".def");
	
	if (cookie_value == "disabled") {
		toggle_element.text("Enable Glossary Links");
		glossary_links.addClass("def_inactive");
		glossary_links.attr("style", "color:#000000;cursor:default;border:0;text-decoration:none;");
	} else {
		toggle_element.text("Disable Glossary Links");
		glossary_links.removeClass("def_inactive");
		glossary_links.removeAttr("style");
	}
	
	// Fire an event to let the Portal ncbipopper code know that we are done.
	jQuery('body').trigger("glossarylinks");
}

function checkGlossLinks(state) {
	var c = 'GlossaryLinks';
	var cv = pmcReadCookie(c);
	var d = 'disabled';
	
	if (state == 'init') {
		toggleglossarylinks_(cv);
	} else if (cv == d) {
		// Was disabled, now enable them.
		pmcEraseCookie(c);
		toggleglossarylinks_();
	} else {
		pmcCreateCookie(c, d, 7);
		toggleglossarylinks_(d);
	}
}

//------ AK - cookie support ---------------------------------------------------
function pmcCreateCookie(name, value, days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime() +(days * 24 * 60 * 60 * 1000));
		var expires = "; expires=" + date.toGMTString();
	} else var expires = "";
	
	document.cookie = name + "=" + value + expires + "; path=/";
}

function pmcReadCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') c = c.substring(1, c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
	}
	
	return null;
}

function pmcEraseCookie(name) {
	pmcCreateCookie(name, "", - 1);
}

//============================================================================
// ITEM POPPER
//============================================================================

// this is an adaption of PBooksGlossary - should probably be merged
BookPopLinks = (function ($) {
	
	// cache of results
	var popperTexts = {
	};
	
	// jQuery set and DOM elemenets of all popper links on the page
	var $popLinks;
	var popLinks;
	
	//getPopper function is called by JIG when the user clicks on a popper item
	var getPopper = function (callback) {
		var href = jQuery(this).attr('data-bk-pop-href');
		if (! href) return;
		
		var others = jQuery(this).attr('data-bk-pop-others');
		var key = (! others) ? href: href + '_' + others.replace(/\s/g, '_');
		
		// If we've already found an this href, then use that
		if (popperTexts[key]) {
			callback();
			return popperTexts[key];
		}
		
		// make a container
		var c = jQuery('<div id="__' + key + '__" class="content"></div>');
		jQuery('body').append(c);
		if (jQuery('.main-content').hasClass('lit-style')) {
			c.addClass('lit-style');
		}
		
		// the taget element that we want
		var target = 'div[id= "' + href.replace(/^[^#]*#(.+)$/, "$1") + '"]';
		
		//if it's on the page, get it
		if (href.match(/^#/)) {
			// clone it, else ncbipopper will hide it
			var lnk = jQuery(this);
			
			var pc = jQuery('body').find(target).clone();
			pc = prepPopper(pc, lnk, href, 1);
			c.append(pc);
			
			if (others) {
				jQuery.each(others.split(' '), function (i, v) {
					var otarget = 'div[id= "' + v + '"]';
					var oc = jQuery('body').find(otarget).clone();
					oc = prepPopper(oc, lnk, '#' + v, 0);
					c.append(oc);
				});
			}
			
			callback();
			popperTexts[key] = c; // store it
			return c;
		}
		
		// otherwise, do an AJAX request, and store the result.
		var ajaxUrl = jQuery(this).attr('data-bk-pop-rid') + ((href.match(/\?/)) ? '&': '?') + 'report=bare';
		jQuery.ajax({
			url: ajaxUrl,
			success: function (data) {
				var bc = jQuery(data).find(target);
				bc = prepPopper(bc, jQuery(this), href, 1);
				c.append(bc);
				
				popperTexts[key] = c; //store it
				callback(c);
			}
		});
	}
	
	function prepPopper(p, lnk, href, isfirst) {
		//add some classes for styling
		p.removeAttr('id');
		if (isfirst == 0) {
			p.addClass('half_rhythm');
		}
		
		//add link for refs
		if (p.hasClass('bk_ref')) {
			var rl = ' [<a target="mainwindow" href="' + href + '">Reference list</a>]';
			p.append(rl);
		}
		//make sure popper closes when links clicked
		var links = p.find('a');
		links.each(function () {
			var l = jQuery(this);
			l.addClass('bk-pop-lnk');
			if (l.hasClass('bk_pop')) {
				var href = l.attr('data-bk-pop-href');
				l.attr('href', href);
			}
		});
		
		jQuery('body').on('click', '.bk-pop-lnk', function () {
			lnk.ncbipopper('close');
		});
		
		//and retun
		return p;
	}
	
	jQuery(document).ready(function () {
		
		if (typeof $popLinks == "undefined") {
			$popLinks = jQuery('a.bk_pop');
			popLinks = $popLinks.get();
		}
		
		// If there are no popper links, then there's nothing to do
		if ($popLinks.length == 0) return;
		
		// process in batches, after a delay.
		var numPopLinks = popLinks.length;
		var batchSize = 100;
		var start = 0;
		function doPopLinkBatch() {
			var end = Math.min(start + batchSize, numPopLinks);
			//console.info("doPopLinkBatch: start = " + start + ", end = " + end);
			
			for (var i = start; i < end;++ i) {
				$this = $(popLinks[i]);
				
				//remove href, so jig does not hide the dest el (needs ticket?)
				var href = $this.attr('href');
				$this.attr('href', '#');
				$this.attr('data-bk-pop-href', href);
				
				// Instantiate and store the results
				$this.ncbipopper({
					destText: getPopper,
					openEvent: 'click',
					closeEvent: 'click',
					hasArrow: true,
					arrowDirection: "top",
					width: "600px",
					triggerPosition: "bottom left",
					destPosition: "top left",
					adjustFit: "autoAdjust",
					//adjustFit: "none",
					isTriggerElementCloseClick: false,
					isDestTextCacheable: false
				});
			}
			
			
			start += batchSize;
			if (start < numPopLinks) {
				window.setTimeout(doPopLinkBatch, 1);
			}
		}
		// Kick off the first batch.
		doPopLinkBatch();
	});
	
	// make getPopper function globally visible (as BookPopLinks.getPopper)
	return {
		"getPopper": getPopper,
		"popperTexts": popperTexts
	};
})(jQuery);