import React from 'react';
import FlatButton from 'material-ui/FlatButton';
import {Link} from 'react-router'
import { Card, CardActions, CardTitle, CardText, CardMenu } from 'react-mdl/lib/Card';
import { Button } from 'react-mdl/lib';
import { Grid, Cell } from 'react-mdl/lib/Grid';

import HomeCard from './HomeCard';

const styles = {
    card: {
	    width: '512px',
        margin: 'auto'
    },
    white: {
        color: '#fff'
    }
};

class Home extends React.Component {

    render() {
        return (
            <div>
                <Grid>
                    <Cell/>
                    <Cell>
                        <HomeCard />
                    </Cell>
                </Grid>

                <Grid>
                    <Cell>
                        <Card shadow={0} style={styles.card}>
                            <CardTitle style={{height: '176px', background: 'url(/images/finishedmatchresults.jpg) center / cover'}} />
                            <CardText>
                                Böngéssz az eddigi forduló eredményei között.
                            </CardText>
                            <CardActions border style={styles.white} >
                                <FlatButton label="Eredmények" containerElement={<Link to="/finishedmatchresults" />} />
                            </CardActions>
                            <CardMenu style={styles.white}></CardMenu>
                        </Card>
                    </Cell>
                    <Cell>
                        <Card shadow={0} style={styles.card}>
                            <CardTitle style={{height: '176px', background: 'url(/images/ranking.jpg) center / cover'}} />
                            <CardText>
                                Nézd meg hol állsz a ranglistán.
                            </CardText>
                            <CardActions border style={styles.white} >
                                <FlatButton label="Ranglista" containerElement={<Link to="/ranking" />} />
                            </CardActions>
                            <CardMenu style={styles.white}></CardMenu>
                        </Card>
                    </Cell>
                    <Cell>
                        <Card shadow={0} style={styles.card}>
                            <CardTitle style={{height: '176px', background: 'url(/images/mybets.jpg) center / cover'}} />
                            <CardText>
                                Tekintsd át az eddig leadott tippjeidet.
                            </CardText>
                            <CardActions border style={styles.white} >
                                <FlatButton label="Tippjeim" containerElement={<Link to="/mybets" />} />
                            </CardActions>
                            <CardMenu style={styles.white}></CardMenu>
                        </Card>
                    </Cell>
                </Grid>
            </div>
        )
    }
}

export default Home;