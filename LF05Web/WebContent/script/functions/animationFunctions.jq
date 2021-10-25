$(document).ready(function(){

    $('#navInfo').on("click",function(){
        $([document.documentElement, document.body]).animate({
            scrollTop: $('#infoOben').offset().top});
    });
    
    
    $('#button1').on("click",function(){
        $('#button1').addClass("rotate-center");
        setTimeout(function(){$('#button1').removeClass("rotate-center");},1500);
    });

    $(document).ready(function(){
        $('#backgroundStart').addClass("fade-in-bottom");
        setTimeout(function(){$('#backgroundStart').removeClass("fade-in-bottom");},1500);
    });

    $(document).ready(function(){
        setTimeout(function(){$('#button1').addClass("flicker-in-1"); }, 600);
        setTimeout(function(){$('#button1').removeClass("flicker-in-1");},3000);
    });

    $('#accordion1').on("click",function(){
        if($('#accordion1').hasClass("shadow-drop-2-center")){
        $('#accordion1').removeClass("shadow-drop-2-center"); 
        }
        else{$('#accordion1').addClass("shadow-drop-2-center")}    
    });

    $('#accordion2').on("click",function(){
        if($('#accordion2').hasClass("shadow-drop-2-center")){
        $('#accordion2').removeClass("shadow-drop-2-center"); 
        }
        else{$('#accordion2').addClass("shadow-drop-2-center")}    
    });

    $('#accordion3').on("click",function(){
        if($('#accordion3').hasClass("shadow-drop-2-center")){
        $('#accordion3').removeClass("shadow-drop-2-center"); 
        }
        else{$('#accordion3').addClass("shadow-drop-2-center")}    
    });

    $(window).on("scroll",function(){
        if($('#infoUnten').visible()){
            setTimeout(function(){$('#infoOben').removeClass("invisible");},800);
            setTimeout(function(){$('#infoOben').addClass("fade-in-bottom");},800);
            //setTimeout(function(){$('#infoOben').removeClass("fade-in-bottom");},1500);  
        }
    });

    $(window).on("resize scroll",function(){
        if($('#infoUnten').visible()){
            setTimeout(function(){$('#infoUnten').removeClass("invisible");},1200);
            setTimeout(function(){$('#infoUnten').addClass("fade-in-bottom");},1200);
        }
    });

    $(document).ready(function(){
    $(".infobox").hover(
        function(){
        $(this).addClass("shadow-pop-tr");
        }
        ,function(){
            $(this).removeClass("shadow-pop-tr");
        }
    );
});


    //Definition Element in Viewport //function .visible//
    $.fn.visible = function(){
        var topElement = $(this).offset().top;
        var downElement = topElement + $(this).outerHeight();
        
        var viewportTop = $(window).scrollTop();
        var viewportDown = viewportTop + $(window).innerHeight();
        return downElement > viewportTop && (topElement < viewportDown - $(this).innerHeight());
    };




});