/**
 * Requires:
 * - jQuery v ... ? 
 * - jQuery Pnotify plugin
 * 
 * @author wiecia
 */

var SJS = SJS || {};

var pnotify_stack = {"dir1": "up", "dir2": "left", "firstpos1": 15, "firstpos2": 30};

SJS.MessagesController = function() {
	
	this.clearAll = function() {
		$.pnotify.remove_all();
	};
	
	this.addError = function(title, message) {
		this.addMessage("error", [title, message]);
	};
	
	this.addInfo = function(title, message) {
		this.addMessage("info", [title, message]);
	};
	
	/**
	 * Adds a visual notification message.
	 * 
	 * @param string type = info|error|notice
	 * @param {Object} content - the actual message
	 */
	this.addMessage = function(type, content) {
		
		title = $.isArray(content) ? content[0] : "Default title";
		message = $.isArray(content) ? content[1] : content;
		
		$.pnotify({
			pnotify_title: title,
            pnotify_text: message,
            pnotify_stack: pnotify_stack,
            pnotify_history: false,
            pnotify_width: "300px",
            pnotify_type: type,
            /*pnotify_addclass: type == 'notice' ? 'ui-state-success' : '',
            pnotify_notice_icon: type == 'notice' ? 'ui-icon ui-icon-success' : 'ui-icon ui-icon-notify',
            pnotify_error_icon: 'ui-icon ui-icon-error',*/
            pnotify_opacity: .9,
            pnotify_delay: 4000,
            pnotify_hide: true // type == 'error' ? false : true
		});
		
	};
	
};
