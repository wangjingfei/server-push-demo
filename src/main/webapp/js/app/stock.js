require({
    baseUrl: '/js',
    paths: {
        'jquery': 'lib/jquery-1.10.2',
        'jquery.cometd': 'lib/cometd/jquery.cometd',
        'org/cometd': 'lib/cometd/cometd'
    },
    shim: {
    	'jquery.cometd': {
    		exports: 'jquery.cometd',
    		deps: ['org/cometd']
    	},
    	'org/cometd': {
    		exports: 'org/cometd'
    	}
    }
}, ['jquery', 'jquery.cometd'], function($, cometd) {
	cometd.configure({
		url: location.protocol + '//' + location.host + config.contextPath + '/cometd',
		logLevel: 'info'
	});
	
	cometd.addListener('/meta/handshake', function(message) {
		if (message.successful) {
			$('#status').append('<div>CometD handshake successful</div>');
			
			$('#subscribe').prop('disabled', false);
			$('#subscribe').click(subscribe);
        } else {
        	$('#status').append('<div>CometD handshake failed</div>');
        }
	});
	
	function subscribe() {
		var message = $('#stock-code').val();
		cometd.publish('/service/hello', message);
		cometd.subscribe('/stock/' + message.toLowerCase(), function(message) {
			var price = message.data;
			
			var code = price.code;
			var oldPrice = price.oldPrice;
			var newPrice = price.newPrice;
			var updateTime = price.updateTime;
			
			$('#stock-price').append('<div>' + code + ', ' + oldPrice + ', ' + newPrice + ', ' + updateTime + '</div>');
		});
	}
	
	cometd.handshake();
});
