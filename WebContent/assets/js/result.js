$().ready(function(){

	var useremail = getData('useremail');
	checkUserLogin(useremail);
	
    var options = {
		valueNames: [ 'name', 'price', 'time', 'location','score'],
		// Since there are no elements in the list, this will be used as template.
		item: '<li><div class="card"><h3 class="name"></h3>'
			 +'<span class="score" data-tooltip="This is a score." '
			 +'data-tooltip-position="top"></span>'
			 +'<p class="price"></p><p class="time"></p>'
			 +'<p class="location"></p></div>'
			 +'<div class="graph-container"><label class="title">Net Sales and change</label>'
			 +'<div class="graph"></div></div></li>'
	};	


	var val = getPromotions(useremail,true);
	var sales = getSales(useremail);
	val.then(function(data){
    	var userList = new List('users', options, data);
    	sales.then(function(data2){
			renderGraph(data2);
		});
	});

});

function getSales(email){
    block();
    return $.ajax({
        type:'POST',
        url:'getSales',
        dataType:'json',
        data:{'email': email},
        complete:function(){
            $.unblockUI(); 
        }
    });
}

function renderGraph(data){
    /*d3 graph*/
    var xy_chart = d3_xy_chart()
        .width(340)
        .height(160)
        .xlabel("Time")
        .ylabel("Sales") ;
    var svg = d3.selectAll(".graph").append("svg")
        .datum(data)
        .call(xy_chart) ;

    function d3_xy_chart() {
        var xlabel = "Time",
            ylabel = "Sales" ;
        
        function chart(selection) {
            selection.each(function(datasets) {
                //
                // Create the plot. 
                //
                var margin = {top: 10, right: 80, bottom: 30, left: 50}, 
                    innerwidth = width - margin.left - margin.right,
                    innerheight = height - margin.top - margin.bottom ;
                
                var x_scale = d3.scale.linear()
                    .range([0, innerwidth])
                    .domain([ d3.min(datasets, function(d) { return d3.min(d.x); }), 
                              d3.max(datasets, function(d) { return d3.max(d.x); }) ]) ;
                
                var y_scale = d3.scale.linear()
                    .range([innerheight, 0])
                    .domain([ d3.min(datasets, function(d) { return d3.min(d.y); }),
                              d3.max(datasets, function(d) { return d3.max(d.y); }) ]) ;

                var color_scale = d3.scale.category10()
                    .domain(d3.range(datasets.length)) ;

                var x_axis = d3.svg.axis()
                    .scale(x_scale)
                    .orient("bottom") ;

                var y_axis = d3.svg.axis()
                    .scale(y_scale)
                    .orient("left") ;

                var x_grid = d3.svg.axis()
                    .scale(x_scale)
                    .orient("bottom")
                    .tickSize(-innerheight)
                    .tickFormat("") ;

                var y_grid = d3.svg.axis()
                    .scale(y_scale)
                    .orient("left") 
                    .tickSize(-innerwidth)
                    .tickFormat("") ;

                var draw_line = d3.svg.line()
                    .interpolate("basis")
                    .x(function(d) { return x_scale(d[0]); })
                    .y(function(d) { return y_scale(d[1]); }) ;

                var svg = d3.select(this)
                    .attr("width", width)
                    .attr("height", height)
                    .append("g")
                    .attr("transform", "translate(" + margin.left + "," + margin.top + ")") ;
                
                svg.append("g")
                    .attr("class", "x grid")
                    .attr("transform", "translate(0," + innerheight + ")")
                    .call(x_grid) ;

                svg.append("g")
                    .attr("class", "y grid")
                    .call(y_grid) ;

                svg.append("g")
                    .attr("class", "x axis")
                    .attr("transform", "translate(0," + innerheight + ")") 
                    .call(x_axis)
                    .append("text")
                    .attr("dy", "-.71em")
                    .attr("x", innerwidth)
                    .style("text-anchor", "end")
                    .text(xlabel) ;
                
                svg.append("g")
                    .attr("class", "y axis")
                    .call(y_axis)
                    .append("text")
                    .attr("transform", "rotate(-90)")
                    .attr("y", 6)
                    .attr("dy", "0.71em")
                    .style("text-anchor", "end")
                    .text(ylabel) ;

                var data_lines = svg.selectAll(".d3_xy_chart_line")
                    .data(datasets.map(function(d) {return d3.zip(d.x, d.y);}))
                    .enter().append("g")
                    .attr("class", "d3_xy_chart_line") ;
                
                data_lines.append("path")
                    .attr("class", "line")
                    .attr("d", function(d) {return draw_line(d); })
                    .attr("stroke", function(_, i) {return color_scale(i);}) ;
                
                data_lines.append("text")
                    .datum(function(d, i) { return {name: datasets[i].label, final: d[d.length-1]}; }) 
                    .attr("transform", function(d) { 
                        return ( "translate(" + x_scale(d.final[0]) + "," + 
                                 y_scale(d.final[1]) + ")" ) ; })
                    .attr("x", 3)
                    .attr("dy", ".35em")
                    .attr("fill", function(_, i) { return color_scale(i); })
                    .text(function(d) { return d.name; }) ;
            });
        }

        chart.width = function(value) {
            if (!arguments.length) return width;
            width = value;
            return chart;
        };

        chart.height = function(value) {
            if (!arguments.length) return height;
            height = value;
            return chart;
        };

        chart.xlabel = function(value) {
            if(!arguments.length) return xlabel ;
            xlabel = value ;
            return chart ;
        } ;

        chart.ylabel = function(value) {
            if(!arguments.length) return ylabel ;
            ylabel = value ;
            return chart ;
        } ;

        return chart;
    }
}

