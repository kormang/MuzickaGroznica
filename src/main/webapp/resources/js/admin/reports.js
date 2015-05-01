/**
 * 
 */

function initreports(params){
	
	$( document ).ajaxError(function( event, jqxhr, settings, thrownError ) {
		$( "div.log" ).text(event);
		$( "div.log" ).text(thrownError);
	});
	
	var loadGrid = function(data, gridSelector, extraCountHeader){
		var source = {
				datatype: "json",
				datafields: [
				    { name: 'name', type: 'string' },
				    { name: 'artist', type: 'string' },
				    { name: 'genre', type: 'string' },
				    { name: 'publishDate', type: 'date' },
				    { name: 'contentType', type: 'string' },
				    { name: 'extraCount', type: 'string'}
				],
				localdata: data
			};
			
			var dataAdapter = new $.jqx.dataAdapter(source);
			$(gridSelector).jqxGrid({
				theme: "bootstap",
				width: '99%',
	            autoheight: true,
	            source: dataAdapter,
	            columns: [
	                 { text: params.headers.name, datafield: 'name' },
	                 { text: params.headers.artist, datafield: 'artist' },
	                 { text: params.headers.genre, datafield: 'genre' },
	                 { text: params.headers.publishDate, datafield: 'publishDate', cellsformat: params.otherlocals.dateformat },
	                 { text: params.headers.contentType, datafield: 'contentType' },
	                 { text: extraCountHeader, datafield: 'extraCount' }
	            ]
			});
	};
	
	var initGridRibbonTab = function(url, gridSelector, extraCountHeader){
			$.get(
					url,
					{},
					function(data, status){
						if(status == "success"){
							loadGrid(data, gridSelector, extraCountHeader);
						} else {
							alert("error");
						}
						
					}
			).fail(function(){alert("Error")});
	};
	
	
	var initMonthlyStats = function(){
		$.get(
				params.monthlyReportDataUrl,
				{},
				function(data, status){
					if(status == "success"){
						$("#num_a_c").html(data.c);
						$("#num_r_u").html(data.u);
					}
				}
		);
	}
	
	
	var initRibbonContent = function (index){
		switch(index){
		case 0:;
			initGridRibbonTab(params.topRatedUrl, "#topRatedGrid", params.headers.rating);
			break;
		case 1:
			initGridRibbonTab(params.mostFavoredUrl, "#mostFavoredGrid", params.headers.favored);
			break;
		case 2:
			initMonthlyStats();
			break;
		}
	};
	

	$("#ribbon").jqxRibbon({ theme: "bootstrap", width: 900, height: 200, mode: "default", position: "top", selectionMode: "click", animationType: "fade", initContent: initRibbonContent });

};