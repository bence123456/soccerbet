import React from 'react';
import ReactDOM from 'react-dom';
import {render} from 'react-dom';
import {Tabs, Tab} from 'material-ui/Tabs';
import baseTheme from 'material-ui/styles/baseThemes/lightBaseTheme';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import SwipeableViews from 'react-swipeable-views';
import injectTapEventPlugin from 'react-tap-event-plugin';
injectTapEventPlugin();
import Matches from './Matches';

const styles = {
  headline: {
    fontSize: 24,
    paddingTop: 16,
    marginBottom: 12,
    fontWeight: 400
  },
  slide: {
    padding: 10,
  },
};

class App extends React.Component {

    getChildContext() {
        return { muiTheme: getMuiTheme(baseTheme) };
    }

    constructor(props) {
        super(props);
        this.state = {
            slideIndex: 0,
        };
    }

    handleChange = (value) => {
        this.setState({
            slideIndex: value,
        });
    };

    render() {
        return (
            <div>
                <Tabs inkBarStyle={{background: '#212121'}} onChange={this.handleChange} value={this.state.slideIndex} >
                    <Tab label="Match Results" value={0} />
                    <Tab label="Upcoming Matches" value={1} />
                    <Tab label="Ranking" value={2} />
                    <Tab label="My bets" value={3} />
                </Tabs>
                <SwipeableViews index={this.state.slideIndex} onChangeIndex={this.handleChange} >
                    <div>
                        <h2 style={styles.headline}>Results of the already finished matches.</h2>
                        Swipe to see the next slide!!!.<br />
                    </div>
                    <div style={styles.slide}>
                        <Matches />
                    </div>
                    <div style={styles.slide}>
                        slide n°3
                    </div>
					<div style={styles.slide}>
                        slide n°4
                    </div>
                </SwipeableViews>
            </div>
        );
    }
}

App.childContextTypes = {
    muiTheme: React.PropTypes.object.isRequired,
};

ReactDOM.render(<App />, document.getElementById('app'));