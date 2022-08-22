(function($) {
    "use strict";
	
		// Preloader 
	jQuery(window).on('load', function() {
		jQuery("#status").fadeOut();
		jQuery("#preloader").delay(350).fadeOut("slow");
	});
	
    var music = {
        initialised: false,
        version: 1.0,
        mobile: false,
        init: function() {
            if (!this.initialised) {
                this.initialised = true;
            } else {
                return;
            }
            /*-------------- Music Functions Calling ---------------------------------------------------
            ------------------------------------------------------------------------------------------------*/
            // this.RTL();
            // this.Menu();
            // this.Player_close();
            // this.Popup();
            this.Testimonial();
            // this.More();
            // this.Nice_select();
            // this.showPlayList();
            // this.volume();
        },
		
        /*-------------- Music Functions definition ---------------------------------------------------
        ---------------------------------------------------------------------------------------------------*/
        RTL: function() {
            var rtl_attr = $("html").attr('dir');
            if (rtl_attr) {
                $('html').find('body').addClass("rtl");
            }
        },
		
	
		
    
		
		
		
        // Player Close On Click
        Player_close: function() {
            $(".ms_player_close").on('click', function() {
                $(".ms_player_wrapper").toggleClass("close_player");
                $("body").toggleClass("main_class")
            })
        },
		
		
					
		
		
        // Pop Up
        Popup: function() {
            $('.clr_modal_btn a').on('click', function() {
                $('#clear_modal').hide();
                $('.modal-backdrop').hide();
                $('body').removeClass("modal-open").css("padding-right", "0px");
            })
            $('.hideCurrentModel').on('click', function() {
                $(this).closest('.modal-content').find('.form_close').trigger('click');
            });
            // Language Popup
            $('.lang_list').find("input[type=checkbox]").on('change', function() {
                if ($('.lang_list').find("input[type=checkbox]:checked").length) {
                    $('.ms_lang_popup .modal-content').addClass('add_lang');
                } else {
                    $('.ms_lang_popup .modal-content').removeClass('add_lang');
                }
            });
        },
		
		
		
		
		
         // Testimonial Slider
        Testimonial: function() {
            if ($('.rd_test_slider').length > 0) {
                $(".rd_test_slider").slick({
                    autoplay: false,
                    autoplaySpeed: 10000,
                    speed: 600,
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    pauseOnHover: false,
                    dots: true,
                    arrows: false,
                    draggable: true,
                    responsive: [{
                        breakpoint: 992,
                        settings: {
                            slidesToShow: 1,
                            slidesToScroll: 1,
                            infinite: true,
                            dots: false
                        }
                    }, ]
                });
            }
        },
		
		
        // More
        More: function() {
            $(".ms_more_icon").on('click', function(e) {
                e.preventDefault();
                e.stopImmediatePropagation();
                if (typeof $(this).attr('data-other') != 'undefined') {
                    var target = $(this).parent().parent();
                } else {
                    var target = $(this).parent();
                }
                if (target.find("ul.more_option").hasClass('open_option')) {
                    target.find("ul.more_option").removeClass('open_option');
                } else {
                    $("ul.more_option.open_option").removeClass('open_option');
                    target.find("ul.more_option").addClass('open_option');
                }
            });
            $(document).on("click", function(e) {
                $("ul.more_option.open_option").removeClass("open_option");
            })
			
			
			
            // On Button Click
            $(".ms_btn.play_btn").on('click', function() {
                $('.ms_btn.play_btn').toggleClass('btn_pause');
            });
            $(document).on('click', '#playlist-wrap ul li .action .que_more', function(e) {
				e.preventDefault();
                e.stopImmediatePropagation();
                $('#playlist-wrap ul li .action .que_more').not($(this)).closest('li').find('.more_option').removeClass('open_option');
                $(this).closest('li').find('.more_option').toggleClass('open_option');
            });
			
			
			
			
			
            // $('.jp-playlist').on('click', function(){
            // $('#playlist-wrap ul li .more_option').removeClass('open_option');
            // });

            $(document).on('click', function(e) {
                if (!$(e.target).closest('.more_option').length && !$(e.target).closest('.action').length) {
                    $('#playlist-wrap .more_option').removeClass('open_option');
                }
                if (!$(e.target).closest('#playlist-wrap').length && !$(e.target).closest('.jp_queue_wrapper').length && !$(e.target).closest('.player_left').length) {
                    $('#playlist-wrap').hide();
                }
            });
            //
            $('.jp_queue_cls').on('click', function(e) {
                $('#playlist-wrap').hide();
            });

        },
		
		
        // Nice Select
        Nice_select: function() {
            if ($('.custom_select').length > 0) {
                $('.custom_select select').niceSelect();
            }
        },
        showPlayList: function() {
            $(document).on('click', '#myPlaylistQueue', function() {
                $('#playlist-wrap').fadeToggle();
            });
            $('#playlist-wrap').on('click', '#myPlaylistQueue', function(event) {
                event.stopPropagation();
            });
        },

        // Volume 
        volume: function() {
            $(".knob-mask .knob").css("transform", "rotateZ(270deg)");
            $(".knob-mask .handle").css("transform", "rotateZ(270deg)");

        }

    };
    $(document).ready(function() {
        music.init();
    });
	
	
	
	
	
    // Preloader Js
    jQuery(window).on('load', function() {
        setTimeout(function() {
            $('body').addClass('loaded');
        }, 500);
        // Li Lenght
        if ($('.jp-playlist ul li').length > 3) {
            $('.jp-playlist').addClass('find_li');
        }
    });
	
	
	
	
    // Window Scroll
    $(window).scroll(function() {
        var wh = window.innerWidth;
        //Go to top
        if ($(this).scrollTop() > 100) {
            $('.gotop').addClass('goto');
        } else {
            $('.gotop').removeClass('goto');
        }
    });
    $(".gotop").on("click", function() {
        $("html, body").animate({
            scrollTop: 0
        }, 600);
        return false
	

		
		
    });
})(jQuery);